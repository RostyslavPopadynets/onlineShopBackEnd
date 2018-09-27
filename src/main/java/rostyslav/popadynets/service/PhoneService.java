package rostyslav.popadynets.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.PhoneDTO;
import rostyslav.popadynets.domain.filters.PhoneFilter;

public interface PhoneService {
	
	public void addPhone(PhoneDTO phoneDTO);
	
	public PhoneDTO findPhoneById(String phoneid);
	
	public List<PhoneDTO> findAllPhones();
	
	public PhoneDTO findByPhoneManufacturer(String manufacturer);

	public PhoneDTO findByPhoneModel(String model);

	public PhoneDTO findByPhoneDisplayDiagonal(BigDecimal displayDiagonal);

	public PhoneDTO findByPhoneResolutionDisplay(String resolutionDisplay);

	public PhoneDTO findByPhoneRam(String ram);

	public PhoneDTO findByPhoneOs(String os);

	public PhoneDTO findByPhoneDriveCapacity(String driveCapacity);

	public PhoneDTO findByPhoneColor(String color);

	public PhoneDTO findByPhoneNumberSim(String numberSim);

	public PhoneDTO findByPhoneMaxFlashMemory(String maxFlashMemory);

	public PhoneDTO findByPhoneFrontalCamera(String frontalCamera);

	public PhoneDTO findByPhoneFlash(boolean flash);

	public PhoneDTO findByPhoneAutofocus(boolean autofocus);

	public PhoneDTO findByPhoneMainCamera(String mainCamera);

	public PhoneDTO findByPhoneCoreNumber(String coreNumber);

	public PhoneDTO findByPhoneBatteryCapacity(String batteryCapacity);

	public PhoneDTO findByPhoneFrequency(BigDecimal frequency);

	public PhoneDTO findByPhonePrice(BigDecimal price);

	public PhoneDTO findByPhoneWeight(BigDecimal weight);
	
	public List<PhoneDTO> findPhoneByUserId(String userId);
	
	public void deletePhone(String phoneId);
	
	public void updatePhone(PhoneDTO phoneDTO);

	public List<PhoneDTO> findAllPhonesBySpecification(PhoneFilter filter, String ret);
	
	public List<PhoneDTO> findAllPhonesByPages(Pageable pageable);
	
	boolean uploadImage(MultipartFile file, String tvID, int numberUrl);
	
}
