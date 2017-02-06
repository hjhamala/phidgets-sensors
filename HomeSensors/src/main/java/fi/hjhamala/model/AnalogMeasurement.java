package fi.hjhamala.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnalogMeasurement implements Temperature{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int value;

	@ManyToOne
	private Sensor sensor;
	
	private LocalDateTime dateTime;
	
	
	public AnalogMeasurement(){
		
	}
	
	public AnalogMeasurement(Sensor sensor, LocalDateTime dateTime, int value) {
		this.sensor = sensor;
		this.dateTime = dateTime;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		
		this.value = (int) ((value -500) * getSensor().getGainFactor() + 500);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public double getCelsiusValue(){
		return measurementValueToCelsiusValue(this.value);
	}
	
	@Override 
	public boolean equals(Object cmp){
		AnalogMeasurement a = (AnalogMeasurement) cmp;
		return 	getSensor().getId() == a.getSensor().getId() && 
				getValue() == a.getValue() && 
				getDateTime().equals(a.getDateTime()) 
				? true : false;
		
	}
	
	
	
	
}
