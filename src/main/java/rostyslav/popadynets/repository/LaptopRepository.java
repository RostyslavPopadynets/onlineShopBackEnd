package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>, JpaSpecificationExecutor<Laptop> {

	Laptop findByLaptopId(String LaptopId);

	Laptop findByManufacturer(String manufacturer);

	Laptop findByModel(String model);

	Laptop findByProcessor(String processor);

	Laptop findByDiagonalScreen(String diagonalScreen);

	Laptop findByRam(String ram);

	Laptop findByDriveCapacity(String driveCapacity);

	Laptop findByColor(String color);

	Laptop findByGraphicAdapter(String graphicAdapter);

	Laptop findByBattery(String battery);

	Laptop findByPrice(BigDecimal price);

	Laptop findByWeight(BigDecimal weight);
	
	boolean existsByLaptopUserId(Long id);
	
	List<Laptop> findByLaptopUserId(Long userId);

}
