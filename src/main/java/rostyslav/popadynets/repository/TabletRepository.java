package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.Tablet;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, Long>, JpaSpecificationExecutor<Tablet> {

	Tablet findByTabletId(String TVId);

	Tablet findByManufacturer(String manufacturer);

	Tablet findByModel(String model);

	Tablet findByDisplayDiagonal(BigDecimal displayDiagonal);

	Tablet findByResolutionDisplay(String resolutionDisplay);

	Tablet findByRam(String ram);

	Tablet findByOs(String os);

	Tablet findByDriveCapacity(String driveCapacity);

	Tablet findByProcessor(String processor);

	Tablet findByColor(String color);

	Tablet findByMaxFlashMemory(String maxFlashMemory);

	Tablet findByFrontalCamera(String frontalCamera);

	Tablet findByMainCamera(String mainCamera);

	Tablet findByCoreNumber(String coreNumber);

	Tablet findByBatteryCapacity(String batteryCapacity);

	Tablet findByFrequency(BigDecimal frequency);

	Tablet findByPrice(BigDecimal price);

	Tablet findByWeight(BigDecimal weight);
	
	boolean existsByTabletUserId(Long id);
	
	List<Tablet> findByTabletUserId(Long userId);

}
