package fi.hjhamala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AnalogAlarm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne
	private Sensor sensor;
	private int alertMin;
	private int alertMax;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public int getAlertMin() {
		return alertMin;
	}

	public void setAlertMin(int alertMin) {
		this.alertMin = alertMin;
	}

	public int getAlertMax() {
		return alertMax;
	}

	public void setAlertMax(int alertMax) {
		this.alertMax = alertMax;
	}

	public boolean checkAlert(double averageTemperature) {
		return  (getAlertMax() > averageTemperature && 
				getAlertMin() < averageTemperature) 
				? false : true;
	}
	
}
