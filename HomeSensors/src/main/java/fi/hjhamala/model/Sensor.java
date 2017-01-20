package fi.hjhamala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sensor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int port;
	private int type;
	
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
	
	@Override
	public boolean equals(Object a){
		Sensor comp = (Sensor) a;
		return (comp.getId() == getId() && 
				comp.getPort() == getPort() && 
				comp.getType()  == getType()) 
				? true : false;
	}
}
