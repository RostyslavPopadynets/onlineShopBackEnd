package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>, JpaSpecificationExecutor<Monitor> {

	Monitor findByMonitorId(String monitorId);

	Monitor findByManufacturer(String manufacturer);

	Monitor findByModel(String model);

	Monitor findByDisplayDiagonal(BigDecimal displayDiagonal);

	Monitor findByMaxResolutionDisplay(String maxResolutionDisplay);

	Monitor findByMatrixType(String matrixType);

	Monitor findByDisplayBrightness(String displayBrightness);

	Monitor findByInterfaces(String interfaces);

	Monitor findByAttitudeParties(String attitudeParties);

	Monitor findByColor(String color);

	Monitor findByWebcam(boolean webcam);

	Monitor findByPrice(BigDecimal price);
	
	boolean existsByMonitorUserId(Long id);
	
	List<Monitor> findByMonitorUserId(Long userId);

}