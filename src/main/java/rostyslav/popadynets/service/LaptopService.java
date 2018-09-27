package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.LaptopDTO;
import rostyslav.popadynets.domain.filters.LaptopFilter;

public interface LaptopService {
	
	public void addLaptop(LaptopDTO laptopDTO);
	
	public LaptopDTO findLaptopById(String laptopid);
	
	public List<LaptopDTO> findAllLaptops();
	
	public LaptopDTO findByLaptopManufacturer(String manufacturer);

	public LaptopDTO findByLaptopModel(String model);

	public LaptopDTO findByLaptopProcessor(String processor);

	public LaptopDTO findByLaptopDiagonalScreen(String diagonalScreen);

	public LaptopDTO findByLaptopRam(String ram);

	public LaptopDTO findByLaptopDriveCapacity(String driveCapacity);

	public LaptopDTO findByLaptopColor(String color);

	public LaptopDTO findByLaptopGraphicAdapter(String graphicAdapter);

	public LaptopDTO findByLaptopBattery(String battery);

	public LaptopDTO findByLaptopPrice(BigDecimal price);

	public LaptopDTO findByLaptopWeight(BigDecimal weight);
	
	public List<LaptopDTO> findLaptopByUserId(String userId);
	
	public void deleteLaptop(String laptopId);
	
	public void updateLaptop(LaptopDTO laptopDTO);

	public List<LaptopDTO> findAllLaptopsBySpecification(LaptopFilter filter, String ret);
	
	public List<LaptopDTO> findAllLaptopsByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
