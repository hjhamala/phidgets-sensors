package fi.hjhamala.component;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.phidgets.PhidgetException;

import fi.hjhamala.HomeSensorsApplication;
import fi.hjhamala.HomeSensorsProperties;
import fi.hjhamala.model.AnalogMeasurement;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.Sensor;
import fi.hjhamala.model.SensorRepository;

@Component
public class ScheduledMeasurement {
	
	private static final Log log = LogFactory.getLog(HomeSensorsApplication.class);
	
	@Autowired
	private AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
	private AnalogReader analogReader;
	
	
	@Scheduled(fixedDelayString="${sensors.temperature-polling-ms}")
	public void saveValues() {
		List<Sensor> sensors = sensorRepository.findAll();
		log.info("Starting reading values");
		Iterator<Sensor> i = sensors.iterator();
		while (i.hasNext()){
			Sensor sensor = i.next();
			AnalogMeasurement measurement = new AnalogMeasurement();
			measurement.setSensor(sensor);
			measurement.setDateTime(LocalDateTime.now());
			try {
				measurement.setValue(analogReader.getValue(sensor.getPort()));
				analogMeasurementRepository.save(measurement);
			} catch (PhidgetException e) {
				log.error("Cannot read value from device");
			}
		}
		
	}
}
