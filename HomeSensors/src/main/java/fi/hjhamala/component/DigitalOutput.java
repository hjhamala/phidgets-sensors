package fi.hjhamala.component;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

import fi.hjhamala.configuration.HomeSensorsProperties;

@Component
public class DigitalOutput {
	
	
	@Autowired
	private PhidgetsKit kit;
	
	
	public void buzzerOutput(int port, long time) throws PhidgetException, InterruptedException{
		long endTime =  System.currentTimeMillis() + time;
		
		while (System.currentTimeMillis()< endTime){
			for (int i = 0; i<100;i++){
				this.kit.getIk().setOutputState(0, true);
				Thread.sleep(1);
				this.kit.getIk().setOutputState(0, false);
				Thread.sleep(1);
			}
			for (int i = 0; i<100;i++){
				this.kit.getIk().setOutputState(0, true);
				Thread.sleep(2);
				this.kit.getIk().setOutputState(0, false);
				Thread.sleep(2);
			}

		}
	}
	
	
}
