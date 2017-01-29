package fi.hjhamala;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.component.ScheduledAlert;
import fi.hjhamala.model.AnalogMeasurement;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.AverageTemperatureStatistics;
import fi.hjhamala.model.Sensor;
import fi.hjhamala.model.SensorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertScheduleTests {
	
	@Autowired
	AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	ScheduledAlert alert;
	
	@Autowired
	SensorRepository sensorRepository;
	
	private static final Log logger = LogFactory
			.getLog(HomeSensorsApplication.class);
	
	@Rollback(true)
	@Test
	@Transactional
	public void testAlerts(){
		sensorRepository.deleteAll();
		analogMeasurementRepository.deleteAll();
		Sensor sensor1 = initializeSensor(0);
		sensor1.setAlertMin(50);
		sensor1.setAlertMax(60);

		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusSeconds(5), 60));
		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusSeconds(6), 30));
		
		assertTrue("Alert should happen", alert.checkAlert());
		
		assertTrue("Sensor should have alerted state", sensor1.isAlerted());
		
	}
	

	
	public Sensor initializeSensor(int port){
			Sensor sensor = new Sensor();
			sensor.setPort(port);
			sensor.setType(0);
			sensor.setGainFactor(1);
			return sensorRepository.save(sensor);
		}
}
	
	
	

