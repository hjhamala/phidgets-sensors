package fi.hjhamala.model;

			 
public class AverageTemperatureStatistics extends Temperature {

		private Sensor sensor;
		private AnalogAlarm analogAlarm;
		private double averageTemperature;
		
		public AverageTemperatureStatistics(Sensor sensor_id, Double averageTemperature) {
			this.sensor = sensor_id;
			this.averageTemperature = averageTemperature;
		}
		
		public AverageTemperatureStatistics(Sensor sensor, AnalogAlarm analogAlarm, Double averageTemperature) {
			this.sensor = sensor;
			this.averageTemperature = averageTemperature;
			this.analogAlarm = analogAlarm;
		}
		
		public Sensor getSensor() {
			return sensor;
		}
		public void setSensor(Sensor sensor) {
			this.sensor = sensor;
		}
		public double getAverageTemperature() {
			return averageTemperature;
		}
		public void setAverageTemperature(Double averageTemperature) {
			this.averageTemperature = averageTemperature;
		}
		
		public AnalogAlarm getAnalogAlarm() {
			return analogAlarm;
		}
		public void setAnalogAlarm(AnalogAlarm analogAlarm) {
			this.analogAlarm = analogAlarm;
		}
		public double getCelciusValue(){
			return measurementValueToCelsiusValue((int) this.averageTemperature);
		}
	
}
