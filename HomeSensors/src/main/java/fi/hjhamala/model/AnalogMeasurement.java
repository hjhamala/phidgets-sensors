package fi.hjhamala.model;

import static org.mockito.Matchers.anyLong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class AnalogMeasurement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int value;

	@ManyToOne
	private Sensor sensor;
	
	private LocalDateTime dateTime;
	
	
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
		return getValue()*0.2222-61.111;
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
