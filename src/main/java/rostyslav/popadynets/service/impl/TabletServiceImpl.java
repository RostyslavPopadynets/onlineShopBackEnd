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
import rostyslav.popadynets.domain.TabletDTO;
import rostyslav.popadynets.domain.filters.TabletFilter;
import rostyslav.popadynets.entity.Tablet;
import rostyslav.popadynets.exceptions.TabletServiceException;
import rostyslav.popadynets.exceptions.models.TabletNotFountException;
import rostyslav.popadynets.repository.TabletRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.TabletService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class TabletServiceImpl implements TabletService {

	@Autowired
	private TabletRepository tabletRepositiry;

	@Autowired
	private UserRepository userRepositiry;
	
	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addTablet(TabletDTO tabletDTO) {

		tabletDTO.setTabletId(stringUtils.generate());
		
		tabletRepositiry.save(modelMapper.map(tabletDTO, Tablet.class));
	}

	@Override
	public TabletDTO findTabletById(String tabletid) {
		Tablet tablet = null;
		try {
			tablet = tabletRepositiry.findByTabletId(tabletid);
		} catch (Exception e) {
			throw new TabletNotFountException(ApplicationConstants.TABLET_NOT_FOUND_EXCEPTION);
		}
		TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
		return tabletDTO;
	}

	@Override
	public List<TabletDTO> findAllTablets() {
		List<Tablet> tablet = tabletRepositiry.findAll();
		List<TabletDTO> tabletDTOs = modelMapper.mapAll(tablet, TabletDTO.class);
		return tabletDTOs;
	}

	@Override
	public TabletDTO findByTabletManufacturer(String manufacturer) {
		Tablet tablet = tabletRepositiry.findByManufacturer(manufacturer);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з таким виробником не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletModel(String model) {
		Tablet tablet = tabletRepositiry.findByModel(model);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою моделю не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletDisplayDiagonal(BigDecimal displayDiagonal) {
		Tablet tablet = tabletRepositiry.findByDisplayDiagonal(displayDiagonal);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою діагоналю не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletResolutionDisplay(String resolutionDisplay) {
		Tablet tablet = tabletRepositiry.findByResolutionDisplay(resolutionDisplay);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з таким розширенням не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletRam(String ram) {
		Tablet tablet = tabletRepositiry.findByRam(ram);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою ram не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletOs(String os) {
		Tablet tablet = tabletRepositiry.findByOs(os);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою ос не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletDriveCapacity(String driveCapacity) {
		Tablet tablet = tabletRepositiry.findByDriveCapacity(driveCapacity);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою пам'яттю не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletProcessor(String processor) {
		Tablet tablet = tabletRepositiry.findByProcessor(processor);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з таким процесором не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletColor(String color) {
		Tablet tablet = tabletRepositiry.findByColor(color);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з таким коляром не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletMaxFlashMemory(String maxFlashMemory) {
		Tablet tablet = tabletRepositiry.findByMaxFlashMemory(maxFlashMemory);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою флеш-пам'яттю не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletFrontalCamera(String frontalCamera) {
		Tablet tablet = tabletRepositiry.findByFrontalCamera(frontalCamera);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою фронт-камерою не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletMainCamera(String mainCamera) {
		Tablet tablet = tabletRepositiry.findByMainCamera(mainCamera);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою головною камерою не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletCoreNumber(String coreNumber) {
		Tablet tablet = tabletRepositiry.findByCoreNumber(coreNumber);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою кількісю ядер не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletBatteryCapacity(String batteryCapacity) {
		Tablet tablet = tabletRepositiry.findByBatteryCapacity(batteryCapacity);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою батареєю не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletFrequency(BigDecimal frequency) {
		Tablet tablet = tabletRepositiry.findByFrequency(frequency);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою частотою не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletPrice(BigDecimal price) {
		Tablet tablet = tabletRepositiry.findByPrice(price);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою ціною не знайдено");
		}
	}

	@Override
	public TabletDTO findByTabletWeight(BigDecimal weight) {
		Tablet tablet = tabletRepositiry.findByWeight(weight);
		if(tablet != null) {
			TabletDTO tabletDTO = modelMapper.map(tablet, TabletDTO.class);
			return tabletDTO;
		} else {
			throw new TabletServiceException("Планшет з такою вагою не знайдено");
		}
	}

	@Override
	public void deleteTablet(String tabletId) {
		Tablet tablet = tabletRepositiry.findByTabletId(tabletId);
		if (tablet != null) {
			tabletRepositiry.delete(tablet);
		} else {
			throw new TabletServiceException("Планшет не знайдено");
		}
		
	}

	@Override
	public void updateTablet(TabletDTO tabletDTO) {
		tabletRepositiry.save(modelMapper.map(tabletDTO, Tablet.class));
	}

	@Override
	public List<TabletDTO> findAllTabletsBySpecification(TabletFilter filter, String ret) {
		return modelMapper.mapAll(tabletRepositiry.findAll(getSpecification(filter, ret)), TabletDTO.class);
	}

	private Specification<Tablet> getSpecification(TabletFilter filter, String ret) {
		return new Specification<Tablet>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Tablet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				if (filter.getSearch().isEmpty()) {
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
					Predicate searchTitleByDisplayDiagonalPredicate = criteriaBuilder.equal(searchByDisplayDiagonal,
							filter.getSearch());
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

				if (ret.equals("processor")) {
					Expression<String> searchByProcessor = root.get("processor");
					Predicate searchTitleByProcessorPredicate = criteriaBuilder.like(searchByProcessor,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByProcessorPredicate);
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
				} else {
					return null;
				}
			}

		};
	}

	@Override
	public List<TabletDTO> findAllTabletsByPages(Pageable pageable) {
		Page<Tablet> tabletPage = tabletRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<Tablet> tablet = tabletPage.getContent();
		List<TabletDTO> tabletDTOs = modelMapper.mapAll(tablet, TabletDTO.class);
		return tabletDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String tabletID, int numberUrl) {
		boolean result = false;

		String imageUrl = cloudinaryService.uploadFile(file, "Tablets");

		Tablet tablet = tabletRepositiry.findByTabletId(tabletID);

		if (imageUrl == null) {
			throw new TabletServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (tablet == null) {
			throw new TabletNotFountException(ApplicationConstants.TABLET_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			tablet.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			tablet.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			tablet.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			tablet.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			tablet.setImageUrl5(imageUrl);
			result = true;
			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		tabletRepositiry.save(tablet);

		return result;
	}

	@Override
	public List<TabletDTO> findTabletByUserId(String userId) {

		if (tabletRepositiry.existsByTabletUserId(userRepositiry.findByUserId(userId).getId())) {
			List<Tablet> tablet = tabletRepositiry.findByTabletUserId(userRepositiry.findByUserId(userId).getId());

			List<TabletDTO> tabletDTOs = modelMapper.mapAll(tablet, TabletDTO.class);
			return tabletDTOs;

		} else {
			throw new TabletServiceException("Планшетів з таким користувачем не знайдено");
		}
	}

}
