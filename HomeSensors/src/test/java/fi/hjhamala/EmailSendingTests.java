package fi.hjhamala;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hjhamala.component.EmailAPI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSendingTests {
	
	@Autowired
	EmailAPI emailApi;

	@Test
	public void testSendEmail() {
		String toAddr = "home.sensors.heikki@gmail.com";
		String fromAddr = "home.sensors.heikki";
		String subject = "Tämä on sensoritestiviesti";
		String body = "runkoteksti";
		boolean exceptionNotThrown = true;
		try {
			emailApi.readyToSendEmail(toAddr, fromAddr, subject, body);
		} catch (Exception e) {
			exceptionNotThrown = false;
		}
		assertTrue("Sending message failed", exceptionNotThrown);
	}
		
	}
	


