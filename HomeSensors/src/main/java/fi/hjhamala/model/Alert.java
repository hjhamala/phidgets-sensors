package fi.hjhamala.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Alert implements Temperature {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne
	private AnalogAlarm analogAlarm;
	private LocalDateTime dateTime;
	private int value;
	private boolean emailSent;
	
	public AnalogAlarm getAlarm() {
		return analogAlarm;
	}
	public void setAlarm(AnalogAlarm analogAlarm) {
		this.analogAlarm = analogAlarm;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isEmailSent() {
		return emailSent;
	}
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}	
	
	public double getCelsiusValue(){
		return measurementValueToCelsiusValue(this.value);
	}
	
}
