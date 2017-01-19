package fi.hjhamala;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sensors")
public class HomeSensorsProperties {
	private int serialNumber;

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
