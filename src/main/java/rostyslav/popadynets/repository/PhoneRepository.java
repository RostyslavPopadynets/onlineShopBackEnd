package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, JpaSpecificationExecutor<Phone> {

	Phone findByPhoneId(String phoneId);

	Phone findByManufacturer(String manufacturer);

	Phone findByModel(String model);

	Phone findByDisplayDiagonal(BigDecimal displayDiagonal);

	Phone findByResolutionDisplay(String resolutionDisplay);

	Phone findByRam(String ram);

	Phone findByOs(String os);

	Phone findByDriveCapacity(String driveCapacity);

	Phone findByColor(String color);

	Phone findByNumberSim(String numberSim);

	Phone findByMaxFlashMemory(String maxFlashMemory);

	Phone findByFrontalCamera(String frontalCamera);

	Phone findByFlash(boolean flash);

	Phone findByAutofocus(boolean autofocus);

	Phone findByMainCamera(String mainCamera);

	Phone findByCoreNumber(String coreNumber);

	Phone findByBatteryCapacity(String batteryCapacity);

	Phone findByFrequency(BigDecimal frequency);

	Phone findByPrice(BigDecimal price);

	Phone findByWeight(BigDecimal weight);
	
	boolean existsByPhoneUserId(Long id);
	
	List<Phone> findByPhoneUserId(Long userId);

}
