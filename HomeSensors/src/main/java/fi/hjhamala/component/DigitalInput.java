package fi.hjhamala.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phidgets.PhidgetException;

@Component
public class DigitalInput {
	
	
	@Autowired
	private PhidgetsKit kit;
	
	public boolean readPort(int port) throws PhidgetException, InterruptedException{
		return kit.getIk().getInputState(port);		
	}
	
	
}
