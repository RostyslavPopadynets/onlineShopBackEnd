package rostyslav.popadynets.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.TV;

@Repository
public interface TVRepository extends JpaRepository<TV, Long>, JpaSpecificationExecutor<TV> {

	TV findByTvId(String tvId);

	TV findByManufacturer(String manufacturer);

	TV findByModel(String model);

	TV findByDisplayDiagonal(BigDecimal displayDiagonal);

	TV findByResolutionDisplay(String resolutionDisplay);

	TV findBySmartPlatform(String smartPlatform);

	TV findByDisplayBrightness(String displayBrightness);

	TV findByColor(String color);

	TV findByHdr(boolean hdr);

	TV findByPrice(BigDecimal price);

	TV findByWeight(BigDecimal weight);
	
	boolean existsByTvUserId(Long id);
	
	List<TV> findByTvUserId(Long userId);
	
}
