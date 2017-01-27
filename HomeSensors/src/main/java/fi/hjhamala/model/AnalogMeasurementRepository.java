package fi.hjhamala.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalogMeasurementRepository extends JpaRepository<AnalogMeasurement, Long> {

		
	@Query("SELECT new fi.hjhamala.model.AverageTemperatureStatistics(a.sensor, AVG(a.value)) FROM AnalogMeasurement a where a.dateTime > :dateTime group by a.sensor.id order by a.sensor.id asc")
	public List<AverageTemperatureStatistics> getAverageTemperatureAfterDateTime(@Param("dateTime") LocalDateTime dateTime);
	
}
