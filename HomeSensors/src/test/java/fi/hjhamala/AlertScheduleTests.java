package fi.hjhamala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
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
import fi.hjhamala.model.AnalogAlarm;
import fi.hjhamala.model.AnalogAlarmRepository;
import fi.hjhamala.model.AnalogMeasurement;
import fi.hjhamala.model.AnalogMeasurementRepository;
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
	
	@Autowired
	AnalogAlarmRepository analogAlarmRepository;
	
	
	@Test
	public void testAlerts(){
		sensorRepository.deleteAll();
		
		Sensor sensor1 = initializeSensor(0);
		sensorRepository.saveAndFlush(sensor1);
		AnalogAlarm alarm = new AnalogAlarm();
		alarm.setSensor(sensor1);
		alarm.setAlertMin(50);
		alarm.setAlertMax(60);
		analogAlarmRepository.saveAndFlush(alarm);
		
		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusSeconds(5), 60));
		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusSeconds(6), 45));
		assertFalse("Alert should not be risen", alert.checkAlert());
		
		// lets add some worrying statistic
		analogMeasurementRepository.saveAndFlush(new AnalogMeasurement(sensor1, LocalDateTime.now().minusMinutes(1), 180));
		List<AnalogMeasurement> al = analogMeasurementRepository.findAll();
		
		assertTrue("Alert should happen", alert.checkAlert());
		assertFalse("Immediatly after the alarm - new alarm should not happen", alert.checkAlert());
		analogMeasurementRepository.deleteAll();
		analogAlarmRepository.deleteAll();
		sensorRepository.deleteAll();
		
	}
	
	
	
	

	
	public Sensor initializeSensor(int port){
			Sensor sensor = new Sensor();
			sensor.setPort(port);
			sensor.setType(0);
			sensor.setGainFactor(1);
			return sensorRepository.save(sensor);
		}
}
	
	
	

