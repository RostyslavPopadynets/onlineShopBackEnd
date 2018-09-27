package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.PC;

@Repository
public interface PCRepository extends JpaRepository<PC, Long>, JpaSpecificationExecutor<PC> {

	PC findByPcId(String pcId);

	PC findByManufacturer(String manufacturer);

	PC findByModel(String model);

	PC findByProcessor(String processor);

	PC findByRam(String ram);

	PC findByOs(String os);

	PC findByDriveCapacity(String driveCapacity);

	PC findByColor(String color);

	PC findByCooling(String cooling);

	PC findByGraphicAdapter(String graphicAdaptering);

	PC findByPrice(BigDecimal price);

	PC findByWeight(BigDecimal weight);
	
	boolean existsByPcUserId(Long id);
	
	List<PC> findByPcUserId(Long userId);

}
