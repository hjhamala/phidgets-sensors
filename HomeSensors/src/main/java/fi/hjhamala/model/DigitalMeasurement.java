package fi.hjhamala.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DigitalMeasurement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private boolean value;
	
	private LocalDateTime dateTime;
	
	@ManyToOne
	private Sensor sensor;
	
	public DigitalMeasurement(){
		
	}
	
	public DigitalMeasurement(Sensor sensor, LocalDateTime dateTime, boolean value) {
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

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {		
		this.value = value;
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
		
	@Override 
	public boolean equals(Object cmp){
		DigitalMeasurement a = (DigitalMeasurement) cmp;
		return 	getSensor().getId() == a.getSensor().getId() && 
				getValue() == a.getValue() && 
				getDateTime().equals(a.getDateTime()) 
				? true : false;
		
	}
	
	
	
	
}
