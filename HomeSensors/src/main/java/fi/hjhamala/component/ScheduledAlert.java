package fi.hjhamala.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fi.hjhamala.configuration.HomeSensorsProperties;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.AverageTemperatureStatistics;
import fi.hjhamala.model.SensorRepository;

@Service
public class ScheduledAlert {
	
	@Autowired
	private AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	private HomeSensorsProperties prop;
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
	EmailAPI emailApi;
		
	@Scheduled(fixedDelayString="${sensors.temperature-alert-polling-ms}")
	public boolean checkAlert() {
		boolean alerted = false;
		List<AverageTemperatureStatistics> averages = analogMeasurementRepository.getAverageTemperatureAfterDateTime(LocalDateTime.now().minusMinutes(prop.getTemperatureAlertAverageDurationMin()));
		
		for (AverageTemperatureStatistics average : averages){
			//TODO Constraint can be checked straight in database with having 
			if (average.getSensor().checkAlert(average.getAverageTemperature()) && !average.getSensor().isAlerted()){
				alerted = true;
				String toAddr = prop.getTemperatureAlertEmailAddress();
				String fromAddr = prop.getEmailAddress();
				String subject = "Hälytys sensoreista";
				String body = "Sensorissa " + average.getSensor().getPort() + " on hälytys.\r\n" + 
				"Lämpötilan keskiarvo on: " + average.getCelciusValue();
				emailApi.readyToSendEmail(toAddr, fromAddr, subject, body);
				average.getSensor().setAlerted(true);
				sensorRepository.save(average.getSensor());
				
			}
		}
		
		return alerted;
	}
}
