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
		Sensor a = new Sensor();
		a.setPort(0);
		a.setType(0);
		sensorRepository.save(a);
		Sensor saved = sensorRepository.findOne(a.getId());
		assertEquals(a, saved);
		sensorRepository.delete(a);
		assertEquals(0, sensorRepository.count());
	}

}

