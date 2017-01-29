package fi.hjhamala.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sensors")
public class HomeSensorsProperties {
	private int serialNumber;
	private int temperaturePollingMs;
	private int temperatureAlertPollingMs;
	private int temperatureAlertAverageDurationMin;
	private String temperatureAlertEmailAddress; 
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

	public int getTemperatureAlertPollingMs() {
		return temperatureAlertPollingMs;
	}

	public void setTemperatureAlertPollingMs(int temperatureAlertPollingMs) {
		this.temperatureAlertPollingMs = temperatureAlertPollingMs;
	}
	
	
	public int getTemperatureAlertAverageDurationMin() {
		return temperatureAlertAverageDurationMin;
	}

	public void setTemperatureAlertAverageDurationMin(int temperatureAlertAverageDurationMin) {
		this.temperatureAlertAverageDurationMin = temperatureAlertAverageDurationMin;
	}

	public String getTemperatureAlertEmailAddress() {
		return temperatureAlertEmailAddress;
	}

	public void setTemperatureAlertEmailAddress(String temperatureAlertEmailAddress) {
		this.temperatureAlertEmailAddress = temperatureAlertEmailAddress;
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
