package fi.hjhamala.service;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.phidgets.*;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;
import com.phidgets.event.TemperatureChangeEvent;
import com.phidgets.event.TemperatureChangeListener;

import fi.hjhamala.HomeSensorsApplication;
@Component
public class AnalogReader {
	private static final Log logger = LogFactory
			.getLog(HomeSensorsApplication.class);
	
	public static void main(String[] args) throws PhidgetException, IOException, InterruptedException{
		InterfaceKitPhidget ik;

		//Example of enabling logging.
		//Phidget.enableLogging(Phidget.PHIDGET_LOG_VERBOSE, null);

		System.out.println(Phidget.getLibraryVersion());

		ik = new InterfaceKitPhidget();
		ik.openAny();
		ik.waitForAttachment();
		System.out.println(ik.getDeviceID());
		int value = ik.getSensorValue(0);
		System.out.println(value*0.2222-61.111);

	}
	
	@Scheduled(fixedDelay=5000)
	public void doSomething() {
	    logger.info("Hep");
	}
}
