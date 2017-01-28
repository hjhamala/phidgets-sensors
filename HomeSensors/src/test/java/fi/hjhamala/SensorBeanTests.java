package fi.hjhamala;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.model.Sensor;
import fi.hjhamala.model.SensorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorBeanTests {
	
	@Autowired
	SensorRepository sensorRepository;

	@Rollback(true)
	@Test
	public void testSaveAndDelete() {
		sensorRepository.deleteAll();
		Sensor a = new Sensor();
		a.setPort(0);
		a.setType(0);
		a.setGainFactor(1.1);
		sensorRepository.save(a);
		Sensor saved = sensorRepository.findOne(a.getId());
		assertEquals(a, saved);
		sensorRepository.delete(a);
		assertEquals("No sensors after delete", 0, sensorRepository.count());
	}
	
	@Rollback(true)
	@Test
	public void testFindActiveSensors() {
		sensorRepository.deleteAll();
		Sensor a = new Sensor();
		a.setActive(true);
		sensorRepository.save(a);
		Sensor b = new Sensor();
		b.setActive(false);
		sensorRepository.save(b);		
		assertEquals("Only one active sensor", sensorRepository.findActiveSensors().size(), 1);
	}
	
}

