package fi.hjhamala.model;

			 
public class AverageTemperatureStatistics {

		private long sensor_id;
		private double averageTemperature;
		
		public AverageTemperatureStatistics(long sensor_id, Double averageTemperature) {
			this.sensor_id = sensor_id;
			this.averageTemperature = averageTemperature;
		}
		public long getSensor_id() {
			return sensor_id;
		}
		public void setSensor_id(long sensor_id) {
			this.sensor_id = sensor_id;
		}
		public double getAverageTemperature() {
			return averageTemperature;
		}
		public void setAverageTemperature(Double averageTemperature) {
			this.averageTemperature = averageTemperature;
		}
		
	
}
