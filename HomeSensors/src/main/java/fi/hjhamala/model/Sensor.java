package fi.hjhamala.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sensor {
	@Id
	private Long id;
	private int port;
	private int type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
}
