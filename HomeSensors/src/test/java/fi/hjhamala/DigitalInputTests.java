package fi.hjhamala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.phidgets.PhidgetException;

import fi.hjhamala.component.AnalogReader;
import fi.hjhamala.component.DigitalInput;
import fi.hjhamala.component.DigitalOutput;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * 
 * @author heikki
 *
 */
public class DigitalInputTests {
	
	@Autowired
	DigitalInput input;

	@Test
	public void testReadInput() throws PhidgetException, InterruptedException {
		assertFalse(input.readPort(1));
	}
	
	@Test
	public void testReadInputTrue() throws PhidgetException, InterruptedException {
		assertTrue(input.readPort(0));
	}

}

