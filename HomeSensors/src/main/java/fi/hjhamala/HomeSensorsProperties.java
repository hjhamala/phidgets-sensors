package fi.hjhamala;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sensors")
public class HomeSensorsProperties {
	private int serialNumber;
	private int temperaturePollingMs;
	private String emailAddress;
	private String emailPassword;

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	
	
	
}
