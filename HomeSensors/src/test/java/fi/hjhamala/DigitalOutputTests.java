package fi.hjhamala;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.component.AnalogReader;
import fi.hjhamala.component.DigitalOutput;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * 
 * @author heikki
 *
 */
public class DigitalOutputTests {
	
	@Autowired
	DigitalOutput output;

	@Test
	public void testBuzzer() {
		boolean exceptionNotThrown = true;
		try {
			output.buzzerOutput(0, 1000);
		} catch (Exception e) {
			exceptionNotThrown = false;
		}
		assertTrue("Cannot read the device", exceptionNotThrown);
	}

}

