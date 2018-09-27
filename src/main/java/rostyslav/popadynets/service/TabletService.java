package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.TabletDTO;
import rostyslav.popadynets.domain.filters.TabletFilter;

public interface TabletService {
	
	public void addTablet(TabletDTO tabletDTO);
	
	public TabletDTO findTabletById(String tabletid);
	
	public List<TabletDTO> findAllTablets();
	
	public TabletDTO findByTabletManufacturer(String manufacturer);

	public TabletDTO findByTabletModel(String model);

	public TabletDTO findByTabletDisplayDiagonal(BigDecimal displayDiagonal);

	public TabletDTO findByTabletResolutionDisplay(String resolutionDisplay);

	public TabletDTO findByTabletRam(String ram);

	public TabletDTO findByTabletOs(String os);

	public TabletDTO findByTabletDriveCapacity(String driveCapacity);

	public TabletDTO findByTabletProcessor(String processor);

	public TabletDTO findByTabletColor(String color);

	public TabletDTO findByTabletMaxFlashMemory(String maxFlashMemory);

	public TabletDTO findByTabletFrontalCamera(String frontalCamera);

	public TabletDTO findByTabletMainCamera(String mainCamera);

	public TabletDTO findByTabletCoreNumber(String coreNumber);

	public TabletDTO findByTabletBatteryCapacity(String batteryCapacity);

	public TabletDTO findByTabletFrequency(BigDecimal frequency);

	public TabletDTO findByTabletPrice(BigDecimal price);

	public TabletDTO findByTabletWeight(BigDecimal weight);
	
	public List<TabletDTO> findTabletByUserId(String userId);
	
	public void deleteTablet(String tabletId);
	
	public void updateTablet(TabletDTO tabletDTO);

	public List<TabletDTO> findAllTabletsBySpecification(TabletFilter filter, String ret);
	
	public List<TabletDTO> findAllTabletsByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
