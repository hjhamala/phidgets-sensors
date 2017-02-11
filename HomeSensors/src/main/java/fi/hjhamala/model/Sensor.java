package fi.hjhamala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sensor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int port;
	private int type;
	private double gainFactor;
	private boolean active;
	private String description;
	@ManyToOne
	private SensorType sensorType;
	
	public Long getId() {
		return id;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public double getGainFactor() {
		return gainFactor;
	}

	public void setGainFactor(double gainFactor) {
		this.gainFactor = gainFactor;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	@Override
	public boolean equals(Object a){
		Sensor comp = (Sensor) a;
		return (comp.getId() == getId() && 
				comp.getPort() == getPort() && 
				comp.getType()  == getType() &&
				comp.getGainFactor() == getGainFactor()) 
				? true : false;
	}

	
}
