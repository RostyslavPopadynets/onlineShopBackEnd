package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.TVDTO;
import rostyslav.popadynets.domain.filters.TVFilter;

public interface TVService {
	
	public void addTV(TVDTO tvDTO);
	
	public TVDTO findTVById(String TVid);
	
	public List<TVDTO> findAllTVs();

	public TVDTO findTVByManufacturer(String manufacturer);

	public TVDTO findTVByModel(String model);

	public TVDTO findTVByDisplayDiagonal(BigDecimal displayDiagonal);

	public TVDTO findTVByResolutionDisplay(String resolutionDisplay);

	public TVDTO findTVBySmartPlatform(String smartPlatform);

	public TVDTO findTVByDisplayBrightness(String displayBrightness);

	public TVDTO findTVByColor(String color);

	public TVDTO findTVByHdr(boolean hdr);

	public TVDTO findTVByPrice(BigDecimal price);

	public TVDTO findTVByWeight(BigDecimal weight);
	
	public List<TVDTO> findTVByUserId(String userId);
	
	public void deleteTV(String TVId);
	
	public void updateTV(TVDTO tvDTO);

	public List<TVDTO> findAllTVsBySpecification(TVFilter filter, String ret);
	
	public List<TVDTO> findAllTVsByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
