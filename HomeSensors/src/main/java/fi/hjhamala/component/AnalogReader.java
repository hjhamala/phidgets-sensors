package fi.hjhamala.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

import fi.hjhamala.configuration.HomeSensorsProperties;
@Component
public class AnalogReader {
	
	@Autowired
	private HomeSensorsProperties properties;	
	
	private InterfaceKitPhidget ik;
	
	public int getValue(int port) throws PhidgetException{
		if (ik == null){
			ik = new InterfaceKitPhidget();
			ik.open(properties.getSerialNumber());
			ik.waitForAttachment(10);
		}
		return ik.getSensorValue(port);
	}

}
