package rostyslav.popadynets.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.constant.ApplicationConstants;
import rostyslav.popadynets.domain.PhoneDTO;
import rostyslav.popadynets.domain.filters.PhoneFilter;
import rostyslav.popadynets.entity.Phone;
import rostyslav.popadynets.exceptions.PhoneServiceException;
import rostyslav.popadynets.exceptions.models.PhoneNotFountException;
import rostyslav.popadynets.repository.PhoneRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.PhoneService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneRepository phoneRepositiry;
	
	@Autowired
	private UserRepository userRepositiry;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addPhone(PhoneDTO phoneDTO) {
		
		phoneDTO.setPhoneId(stringUtils.generate());
		
		phoneRepositiry.save(modelMapper.map(phoneDTO, Phone.class));
	}

	@Override
	public PhoneDTO findPhoneById(String phoneid) {
		Phone phone = null;
		try {
			phone = phoneRepositiry.findByPhoneId(phoneid);
		} catch (Exception e) {
			throw new PhoneNotFountException(ApplicationConstants.PHONE_NOT_FOUND_EXCEPTION);
		}
		PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
		return phoneDTO;
	}

	@Override
	public List<PhoneDTO> findAllPhones() {
		List<Phone> phone = phoneRepositiry.findAll();
		List<PhoneDTO> phoneDTOs = modelMapper.mapAll(phone, PhoneDTO.class);
		return phoneDTOs;
	}

	@Override
	public PhoneDTO findByPhoneManufacturer(String manufacturer) {
		Phone phone = phoneRepositiry.findByManufacturer(manufacturer);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з таким виробником не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneModel(String model) {
		Phone phone = phoneRepositiry.findByModel(model);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою моделю не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneDisplayDiagonal(BigDecimal displayDiagonal) {
		Phone phone = phoneRepositiry.findByDisplayDiagonal(displayDiagonal);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою діагоналю не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneResolutionDisplay(String resolutionDisplay) {
		Phone phone = phoneRepositiry.findByResolutionDisplay(resolutionDisplay);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з таким розширенням не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneRam(String ram) {
		Phone phone = phoneRepositiry.findByRam(ram);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою ram не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneOs(String os) {
		Phone phone = phoneRepositiry.findByOs(os);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою ос не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneDriveCapacity(String driveCapacity) {
		Phone phone = phoneRepositiry.findByDriveCapacity(driveCapacity);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою пам'яттю не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneColor(String color) {
		Phone phone = phoneRepositiry.findByColor(color);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з таким коляром не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneNumberSim(String numberSim) {
		Phone phone = phoneRepositiry.findByNumberSim(numberSim);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою кількістю сім-карт не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneMaxFlashMemory(String maxFlashMemory) {
		Phone phone = phoneRepositiry.findByMaxFlashMemory(maxFlashMemory);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою флеш-пам'яттю не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneFrontalCamera(String frontalCamera) {
		Phone phone = phoneRepositiry.findByFrontalCamera(frontalCamera);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою фронт-камерою не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneFlash(boolean flash) {
		Phone phone = phoneRepositiry.findByFlash(flash);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з спишкою не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneAutofocus(boolean autofocus) {
		Phone phone = phoneRepositiry.findByAutofocus(autofocus);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з автофокусом не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneMainCamera(String mainCamera) {
		Phone phone = phoneRepositiry.findByMainCamera(mainCamera);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою головною камерою не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneCoreNumber(String coreNumber) {
		Phone phone = phoneRepositiry.findByCoreNumber(coreNumber);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою кількістю ядер не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneBatteryCapacity(String batteryCapacity) {
		Phone phone = phoneRepositiry.findByBatteryCapacity(batteryCapacity);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою батареєю не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneFrequency(BigDecimal frequency) {
		Phone phone = phoneRepositiry.findByFrequency(frequency);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою частотою не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhonePrice(BigDecimal price) {
		Phone phone = phoneRepositiry.findByPrice(price);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою ціною не знайдено");
		}	
	}

	@Override
	public PhoneDTO findByPhoneWeight(BigDecimal weight) {
		Phone phone = phoneRepositiry.findByWeight(weight);
		if(phone != null) {
			PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
			return phoneDTO;
		} else {
			throw new PhoneServiceException("Смартфон з такою вагою не знайдено");
		}	
	}

	@Override
	public void deletePhone(String phoneId) {
		Phone phone = phoneRepositiry.findByPhoneId(phoneId);
		if (phone != null) {
			phoneRepositiry.delete(phone);
		} else {
			throw new PhoneServiceException("Смартфон не знайдено");
		}
	}

	@Override
	public void updatePhone(PhoneDTO phoneDTO) {
		phoneRepositiry.save(modelMapper.map(phoneDTO, Phone.class));
	}

	@Override
	public List<PhoneDTO> findAllPhonesBySpecification(PhoneFilter filter, String ret) {
		return modelMapper.mapAll(phoneRepositiry.findAll(getSpecification(filter, ret)), PhoneDTO.class);
	}

	private Specification<Phone> getSpecification(PhoneFilter filter, String ret) {
		return new Specification<Phone>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Phone> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				if (filter.getSearch().isEmpty()) {
					System.err.println("Filter is null");
					return null;
				}

				if (ret.equals("manufacturer")) {
					Expression<String> searchByManufacturer = root.get("manufacturer");
					Predicate searchTitleByManufacturerPredicate = criteriaBuilder.like(searchByManufacturer,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByManufacturerPredicate);
				}

				if (ret.equals("model")) {
					Expression<String> searchByModel = root.get("model");
					Predicate searchTitleByModelPredicate = criteriaBuilder.like(searchByModel,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByModelPredicate);
				}

				if (ret.equals("displayDiagonal")) {
					Expression<BigDecimal> searchByDisplayDiagonal = root.get("displayDiagonal");
					Predicate searchTitleByDisplayDiagonalPredicate = criteriaBuilder.greaterThanOrEqualTo(
							searchByDisplayDiagonal, BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByDisplayDiagonalPredicate);
				}

				if (ret.equals("resolutionDisplay")) {
					Expression<String> searchByResolutionDisplay = root.get("resolutionDisplay");
					Predicate searchTitleByResolutionDisplayPredicate = criteriaBuilder.like(searchByResolutionDisplay,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByResolutionDisplayPredicate);
				}
				if (ret.equals("color")) {
					Expression<String> searchByColor = root.get("color");
					Predicate searchTitleByColorPredicate = criteriaBuilder.like(searchByColor,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByColorPredicate);
				}
				if (ret.equals("driveCapacity")) {
					Expression<String> searchByDriveCapacity = root.get("driveCapacity");
					Predicate searchTitleByDriveCapacityPredicate = criteriaBuilder.like(searchByDriveCapacity,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByDriveCapacityPredicate);
				}
				if (ret.equals("price")) {
					Expression<BigDecimal> searchByPrice = root.get("price");
					Predicate searchTitleByPricePredicate = criteriaBuilder.lessThanOrEqualTo(searchByPrice,
							BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByPricePredicate);
				}
				if (ret.equals("weight")) {
					Expression<BigDecimal> searchByWeight = root.get("weight");
					Predicate searchTitleByWeightPredicate = criteriaBuilder.lessThanOrEqualTo(searchByWeight,
							BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByWeightPredicate);
				}
				if (ret.equals("ram")) {
					Expression<String> searchByRam = root.get("ram");
					Predicate searchTitleByRamPredicate = criteriaBuilder.like(searchByRam,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByRamPredicate);
				}
				if (ret.equals("os")) {
					Expression<String> searchByOs = root.get("os");
					Predicate searchTitleByOsPredicate = criteriaBuilder.like(searchByOs,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByOsPredicate);
				}
				if (ret.equals("numberSim")) {
					Expression<String> searchByNumberSim = root.get("numberSim");
					Predicate searchTitleByNumberSimPredicate = criteriaBuilder.like(searchByNumberSim,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByNumberSimPredicate);
				}
				if (ret.equals("maxFlashMemory")) {
					Expression<String> searchByMaxFlashMemory = root.get("maxFlashMemory");
					Predicate searchTitleByMaxFlashMemoryPredicate = criteriaBuilder.like(searchByMaxFlashMemory,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByMaxFlashMemoryPredicate);
				}
				if (ret.equals("frontalCamera")) {
					Expression<String> searchByFrontalCamera = root.get("frontalCamera");
					Predicate searchTitleByFrontalCameraPredicate = criteriaBuilder.like(searchByFrontalCamera,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByFrontalCameraPredicate);
				}
				if (ret.equals("mainCamera")) {
					Expression<String> searchByMainCamera = root.get("mainCamera");
					Predicate searchTitleByMainCameraPredicate = criteriaBuilder.like(searchByMainCamera,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByMainCameraPredicate);
				}
				if (ret.equals("coreNumber")) {
					Expression<String> searchByCoreNumber = root.get("coreNumber");
					Predicate searchTitleByCoreNumberPredicate = criteriaBuilder.like(searchByCoreNumber,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByCoreNumberPredicate);
				}
				if (ret.equals("frequency")) {
					Expression<BigDecimal> searchByFrequency = root.get("frequency");
					Predicate searchTitleByFrequencyPredicate = criteriaBuilder.greaterThanOrEqualTo(searchByFrequency,
							BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByFrequencyPredicate);
				}
				if (ret.equals("batteryCapacity")) {
					Expression<String> searchByBatteryCapacity = root.get("batteryCapacity");
					Predicate searchTitleByBatteryCapacityPredicate = criteriaBuilder.like(searchByBatteryCapacity,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByBatteryCapacityPredicate);
				}
				if (ret.equals("flash")) {
					Expression<Boolean> searchByFlash = root.get("flash");
					Predicate searchTitleByFlashPredicate = criteriaBuilder.equal(searchByFlash, true);
					return criteriaBuilder.and(searchTitleByFlashPredicate);
				}
				if (ret.equals("autofocus")) {
					Expression<Boolean> searchByAutofocus = root.get("autofocus");
					Predicate searchTitleByAutofocusPredicate = criteriaBuilder.equal(searchByAutofocus, true);
					return criteriaBuilder.and(searchTitleByAutofocusPredicate);
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public List<PhoneDTO> findAllPhonesByPages(Pageable pageable) {
		Page<Phone> phonePage = phoneRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<Phone> phone = phonePage.getContent();
		List<PhoneDTO> phoneDTOs = modelMapper.mapAll(phone, PhoneDTO.class);
		return phoneDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String phoneId, int numberUrl) {
		boolean result = false;
		String imageUrl = cloudinaryService.uploadFile(file, "Phones");
		Phone phone = phoneRepositiry.findByPhoneId(phoneId);

		if (imageUrl == null) {
			throw new PhoneServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (phone == null) {
			throw new PhoneNotFountException(ApplicationConstants.PHONE_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			phone.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			phone.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			phone.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			phone.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			phone.setImageUrl5(imageUrl);
			result = true;
			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		phoneRepositiry.save(phone);

		return result;
	}

	@Override
	public List<PhoneDTO> findPhoneByUserId(String userId) {
		List<Phone> phone = phoneRepositiry.findByPhoneUserId(userRepositiry.findByUserId(userId).getId());
		if(!phone.isEmpty()) {
			List<PhoneDTO> phoneDTOs = modelMapper.mapAll(phone, PhoneDTO.class);
			return phoneDTOs;
		} else {
			throw new PhoneServiceException("Смартфони з таким користувачем не знайдено");
		}	
		
	}

}
