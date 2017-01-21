package fi.hjhamala.component;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import fi.hjhamala.HomeSensorsProperties;
@Component
public class AnalogReader {
	private static final Log logger = LogFactory
			.getLog(HomeSensorsApplication.class);
	
	@Autowired
	private HomeSensorsProperties properties;	
	
	public int getValue(int port) throws PhidgetException{
		InterfaceKitPhidget ik = new InterfaceKitPhidget();
		ik.open(properties.getSerialNumber());
		ik.waitForAttachment();
		int value = ik.getSensorValue(port);
		ik.close();
		return value;
	}

}
