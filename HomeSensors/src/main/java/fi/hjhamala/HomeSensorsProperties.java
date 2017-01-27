package fi.hjhamala;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sensors")
public class HomeSensorsProperties {
	private int serialNumber;
	private int temperaturePollingMs;

	public int getSerialNumber() {
		return serialNumber;
	}

	public int getTemperaturePollingMs() {
		return temperaturePollingMs;
	}

	public void setTemperaturePollingMs(int temperaturePollingMs) {
		this.temperaturePollingMs = temperaturePollingMs;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
