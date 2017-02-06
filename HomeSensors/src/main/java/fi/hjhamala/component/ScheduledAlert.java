package fi.hjhamala.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fi.hjhamala.configuration.HomeSensorsProperties;
import fi.hjhamala.model.Alert;
import fi.hjhamala.model.AlertRepository;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.AverageTemperatureStatistics;

@Service
public class ScheduledAlert {
	
	@Autowired
	private AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	private HomeSensorsProperties prop;
		
	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	EmailAPI emailApi;
		
	@Scheduled(fixedDelayString="${sensors.temperature-alert-polling-ms}")
	public boolean checkAlert() {
		List<AverageTemperatureStatistics> averages = analogMeasurementRepository.getAverageTemperaturesAfterDateTimeWithAlarms(LocalDateTime.now().minusMinutes(prop.getTemperatureAlertAverageDurationMin()));
		boolean alerted = false;
		
		for (AverageTemperatureStatistics average : averages){
			if (average.getAnalogAlarm().checkAlert(average.getAverageTemperature())){
				List<Alert> alerts = alertRepository.getAlertsByPeriod(LocalDateTime.now().minusMinutes(prop.getAlertDelayBeforeNewAlertMin()), average.getAnalogAlarm());
					if (alerts.isEmpty()){
						alerted = true;
						Alert alert = new Alert();
						alert.setAlarm(average.getAnalogAlarm());
						alert.setDateTime(LocalDateTime.now());
						alert.setValue((int) average.getAverageTemperature());
						alertRepository.save(alert);
					}
				}
			}

		return alerted;
	}
}
