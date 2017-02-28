package fi.hjhamala.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

import fi.hjhamala.configuration.HomeSensorsProperties;
@Component
public class AnalogReader {
	
	@Autowired
	private PhidgetsKit ik;
	
	public int getValue(int port) throws PhidgetException{
		return ik.getIk().getSensorValue(port);
	}

}
