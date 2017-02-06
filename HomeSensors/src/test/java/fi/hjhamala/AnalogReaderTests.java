package fi.hjhamala;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.component.AnalogReader;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * These tests work only when temperature phidget is on port 0 and port 1 is empty
 * @author heikki
 *
 */
public class AnalogReaderTests {
	
	@Autowired
	AnalogReader reader;

	@Rollback(true)
	@Test
	public void testRead() {
		boolean exceptionNotThrown = true;
		try {
			int value = reader.getValue(0);
			double celsius = value*0.2222-61.111;
			// Check that value is between normal room temperature
			assertTrue(celsius > 20 &&  celsius < 30);
			value = reader.getValue(1);
			// Empty ports value should be near zero
			assertTrue(value < 10);
		} catch (Exception e) {
			exceptionNotThrown = false;
		}
		assertTrue("Cannot read the device", exceptionNotThrown);
	}

}

