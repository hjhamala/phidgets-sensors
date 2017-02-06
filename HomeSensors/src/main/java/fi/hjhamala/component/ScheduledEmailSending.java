package fi.hjhamala.component;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fi.hjhamala.configuration.HomeSensorsProperties;
import fi.hjhamala.model.Alert;
import fi.hjhamala.model.AlertRepository;
import fi.hjhamala.model.Temperature;

@Service
public class ScheduledEmailSending {
	
	@Autowired
	private HomeSensorsProperties prop;
	
	@Autowired
	private AlertRepository alertRepository;
	
	
	@Autowired
	EmailAPI emailApi;
		
	@Scheduled(fixedDelayString="${sensors.temperature-alert-polling-ms}")
	@Transactional
	public boolean checkAlertsNotSent() {
		boolean sent = false;
		List<Alert> alarmsNotSent = alertRepository.getAlertsNotSent();
		for (Iterator<Alert> iterator = alarmsNotSent.iterator(); iterator.hasNext();) {
			Alert alert = iterator.next();
			String toAddr = prop.getTemperatureAlertEmailAddress();
			String fromAddr = prop.getEmailAddress();
			String subject = "Hälytys sensoreista";
			String body = "Sensorissa " + alert.getAlarm().getSensor().getPort() + " on hälytys.\r\n" + 
			"Lämpötilan keskiarvo on: " + Temperature.measurementValueToCelsiusValue(alert.getValue());
			emailApi.readyToSendEmail(toAddr, fromAddr, subject, body);
			alert.setEmailSent(true);
			alertRepository.save(alert);
		}
	return sent;
	}
}
