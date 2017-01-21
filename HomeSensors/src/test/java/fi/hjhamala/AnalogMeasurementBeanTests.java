package fi.hjhamala;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.model.AnalogMeasurement;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.Sensor;
import fi.hjhamala.model.SensorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalogMeasurementBeanTests {
	
	@Autowired
	AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	SensorRepository sensorRepository;
	
	@Rollback(true)
	@Test
	@Transactional
	public void testSavingAndCelsiusConversion() {
		Sensor sensor = new Sensor();
		sensor.setPort(0);
		sensor.setType(0);
		sensorRepository.save(sensor);
		
		AnalogMeasurement measurement = new AnalogMeasurement();
		measurement.setSensor(sensor);
		measurement.setDateTime(LocalDateTime.now());
		measurement.setValue(68);
		analogMeasurementRepository.save(measurement);
		
		AnalogMeasurement savedMeasurement = analogMeasurementRepository.getOne(measurement.getId());
		assertEquals(68, savedMeasurement.getValue());
		assertEquals(68*0.2222-61.111, savedMeasurement.getCelsiusValue(),  0);
		assertEquals(savedMeasurement, measurement);
	}

}

