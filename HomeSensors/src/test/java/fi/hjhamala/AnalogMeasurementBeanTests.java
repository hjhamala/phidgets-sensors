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

import fi.hjhamala.model.AnalogMeasurement;
import fi.hjhamala.model.AnalogMeasurementRepository;
import fi.hjhamala.model.AverageTemperatureStatistics;
import fi.hjhamala.model.Sensor;
import fi.hjhamala.model.SensorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalogMeasurementBeanTests {
	
	@Autowired
	AnalogMeasurementRepository analogMeasurementRepository;

	@Autowired
	SensorRepository sensorRepository;
	
	private static final Log logger = LogFactory
			.getLog(HomeSensorsApplication.class);
	
	
	@Rollback(true)
	@Test
	@Transactional
	public void testSavingAndCelsiusConversion() {
		Sensor sensor = initializeSensor(0);

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
	
	@Rollback(true)
	@Test
	@Transactional
	public void testGainFactor(){
		Sensor sensor = new Sensor();
		sensor.setPort(0);
		sensor.setType(0);
		sensor.setGainFactor(1.026);
		sensorRepository.save(sensor);
		AnalogMeasurement measurement = new AnalogMeasurement();
		measurement.setSensor(sensor);
		measurement.setDateTime(LocalDateTime.now());
		measurement.setValue(68);
		int expected = (int) ((68 -500) * 1.026 + 500);
		assertEquals(expected, measurement.getValue());
	}

	@Rollback(true)
	@Test
	@Transactional
	public void testAverageTemperatureStatistics(){
		sensorRepository.deleteAll();
		analogMeasurementRepository.deleteAll();
		Sensor sensor1 = initializeSensor(0);
		Sensor sensor2 = initializeSensor(1);
		
		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusMinutes(1), 60));
		analogMeasurementRepository.save(new AnalogMeasurement(sensor1, LocalDateTime.now().minusMinutes(2), 30));
		analogMeasurementRepository.save(new AnalogMeasurement(sensor2, LocalDateTime.now().minusMinutes(1), 30));
		analogMeasurementRepository.save(new AnalogMeasurement(sensor2, LocalDateTime.now().minusMinutes(2), 10));
		List<AverageTemperatureStatistics> avg = analogMeasurementRepository.getAverageTemperatureAfterDateTime(LocalDateTime.now().minusMinutes(3));
		
		AverageTemperatureStatistics sensor1Avg= avg.get(0);
		AverageTemperatureStatistics sensor2Avg= avg.get(1);
		
		assertEquals(45,sensor1Avg.getAverageTemperature(), 0);
		assertEquals(20,sensor2Avg.getAverageTemperature(), 0);
		
		
	}
	
	public Sensor initializeSensor(int port){
			Sensor sensor = new Sensor();
			sensor.setPort(port);
			sensor.setType(0);
			sensor.setGainFactor(1);
			return sensorRepository.save(sensor);
		}
}
	
	
	

