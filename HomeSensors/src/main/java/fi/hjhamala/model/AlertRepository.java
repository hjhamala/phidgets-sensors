package fi.hjhamala.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
	
	@Query("SELECT a from Alert a where a.emailSent = false")
	public List<Alert> getAlertsNotSent();
	
	@Query("SELECT a from Alert a where a.analogAlarm = :alarm AND a.dateTime > :dateTime ")
	public List<Alert> getAlertsByPeriod(@Param("dateTime") LocalDateTime dateTime, @Param("alarm") AnalogAlarm alarm);
}
