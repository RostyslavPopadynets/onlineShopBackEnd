package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.PCDTO;
import rostyslav.popadynets.domain.filters.PCFilter;

public interface PCService {
	
	public void addPC(PCDTO PCDTO);
	
	public PCDTO findPCById(String pcid);
	
	public List<PCDTO> findAllPCs();

	public PCDTO findByPCManufacturer(String manufacturer);

	public PCDTO findByPCModel(String model);

	public PCDTO findByPCProcessor(String processor);

	public PCDTO findByPCRam(String ram);

	public PCDTO findByPCOs(String os);

	public PCDTO findByPCDriveCapacity(String driveCapacity);

	public PCDTO findByPCColor(String color);

	public PCDTO findByPCCooling(String cooling);

	public PCDTO findByPCGraphicAdapter(String graphicAdaptering);

	public PCDTO findByPCPrice(BigDecimal price);

	public PCDTO findByPCWeight(BigDecimal weight);
	
	public List<PCDTO> findPCByUserId(String userId);

	public void deletePC(String pcId);
	
	public void updatePC(PCDTO pcDTO);

	public List<PCDTO> findAllPCsBySpecification(PCFilter filter, String ret);
	
	public List<PCDTO> findAllPCsByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
