package fi.hjhamala.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

import fi.hjhamala.configuration.HomeSensorsProperties;

@Service
public class PhidgetsKit {
	
	@Autowired
	private HomeSensorsProperties properties;	
	
	private InterfaceKitPhidget ik;
	
	

	public InterfaceKitPhidget getIk() throws PhidgetException {
		if (ik == null){
			this.ik = new InterfaceKitPhidget();			
			this.ik.open(properties.getSerialNumber());
			this.ik.waitForAttachment(100);
		}
		return ik;
	}
	
}
