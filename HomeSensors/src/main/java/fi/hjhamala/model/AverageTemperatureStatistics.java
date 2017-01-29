package fi.hjhamala.model;

			 
public class AverageTemperatureStatistics extends Temperature {

		private Sensor sensor;
		private double averageTemperature;
		
		public AverageTemperatureStatistics(Sensor sensor_id, Double averageTemperature) {
			this.sensor = sensor_id;
			this.averageTemperature = averageTemperature;
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
		
		public double getCelciusValue(){
			return measurementValueToCelsiusValue((int) this.averageTemperature);
		}
	
}
