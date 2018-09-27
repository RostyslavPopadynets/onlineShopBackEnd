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
import rostyslav.popadynets.domain.LaptopDTO;
import rostyslav.popadynets.domain.filters.LaptopFilter;
import rostyslav.popadynets.entity.Laptop;
import rostyslav.popadynets.exceptions.LaptopServiceException;
import rostyslav.popadynets.exceptions.models.LaptopNotFountException;
import rostyslav.popadynets.repository.LaptopRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.LaptopService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class LaptopServiceImpl implements LaptopService {

	@Autowired
	private LaptopRepository laptopRepositiry;

	@Autowired
	private UserRepository userRepositiry;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addLaptop(LaptopDTO laptopDTO) {
		laptopDTO.setLaptopId(stringUtils.generate());

		laptopRepositiry.save(modelMapper.map(laptopDTO, Laptop.class));
	}

	@Override
	public LaptopDTO findLaptopById(String laptopid) {
		Laptop laptop = null;
		try {
			laptop = laptopRepositiry.findByLaptopId(laptopid);
		} catch (Exception e) {
			throw new LaptopNotFountException(ApplicationConstants.LAPTOP_NOT_FOUND_EXCEPTION);
		}
		LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
		return laptopDTO;
	}

	@Override
	public List<LaptopDTO> findAllLaptops() {
		List<Laptop> laptop = laptopRepositiry.findAll();
		List<LaptopDTO> laptopDTOs = modelMapper.mapAll(laptop, LaptopDTO.class);
		return laptopDTOs;
	}

	@Override
	public LaptopDTO findByLaptopManufacturer(String manufacturer) {
		Laptop laptop = laptopRepositiry.findByManufacturer(manufacturer);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з таким виробником не знайдено");
		}

	}

	@Override
	public LaptopDTO findByLaptopModel(String model) {
		Laptop laptop = laptopRepositiry.findByModel(model);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою моделю не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopProcessor(String processor) {
		Laptop laptop = laptopRepositiry.findByProcessor(processor);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з таким процесором не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopDiagonalScreen(String diagonalScreen) {
		Laptop laptop = laptopRepositiry.findByDiagonalScreen(diagonalScreen);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з таким розширенням не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopRam(String ram) {
		Laptop laptop = laptopRepositiry.findByRam(ram);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою ram не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopDriveCapacity(String driveCapacity) {
		Laptop laptop = laptopRepositiry.findByDriveCapacity(driveCapacity);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою пам'яттю не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopColor(String color) {
		Laptop laptop = laptopRepositiry.findByColor(color);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з таким коляром не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopGraphicAdapter(String graphicAdapter) {
		Laptop laptop = laptopRepositiry.findByGraphicAdapter(graphicAdapter);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою відеокартою не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopBattery(String battery) {
		Laptop laptop = laptopRepositiry.findByBattery(battery);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою батареєю не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopPrice(BigDecimal price) {
		Laptop laptop = laptopRepositiry.findByPrice(price);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою ціною не знайдено");
		}
	}

	@Override
	public LaptopDTO findByLaptopWeight(BigDecimal weight) {
		Laptop laptop = laptopRepositiry.findByWeight(weight);
		if (laptop != null) {
			LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);
			return laptopDTO;
		} else {
			throw new LaptopServiceException("Ноутбук з такою вагою не знайдено");
		}
	}

	@Override
	public void deleteLaptop(String laptopId) {
		Laptop laptop = laptopRepositiry.findByLaptopId(laptopId);
		if (laptop != null) {
			laptopRepositiry.delete(laptop);
		} else {
			throw new LaptopServiceException("Ноутбук не знайдено");
		}
	}

	@Override
	public void updateLaptop(LaptopDTO laptopDTO) {
		laptopRepositiry.save(modelMapper.map(laptopDTO, Laptop.class));
	}

	@Override
	public List<LaptopDTO> findAllLaptopsBySpecification(LaptopFilter filter, String ret) {
		return modelMapper.mapAll(laptopRepositiry.findAll(getSpecification(filter, ret)), LaptopDTO.class);
	}

	private Specification<Laptop> getSpecification(LaptopFilter filter, String ret) {
		return new Specification<Laptop>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Laptop> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

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
				if (ret.equals("diagonalScreen")) {
					Expression<BigDecimal> searchByDiagonalScreen = root.get("diagonalScreen");
					Predicate searchTitleByDiagonalScreenPredicate = criteriaBuilder.greaterThanOrEqualTo(
							searchByDiagonalScreen, BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByDiagonalScreenPredicate);
				}
				if (ret.equals("ram")) {
					Expression<String> searchByRam = root.get("ram");
					Predicate searchTitleByRamPredicate = criteriaBuilder.like(searchByRam,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByRamPredicate);
				}
				if (ret.equals("processor")) {
					Expression<String> searchByProcessor = root.get("processor");
					Predicate searchTitleByProcessorPredicate = criteriaBuilder.like(searchByProcessor,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByProcessorPredicate);
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
				if (ret.equals("graphicAdapter")) {
					Expression<String> searchByGraphicAdapter = root.get("graphicAdapter");
					Predicate searchTitleByGraphicAdapterPredicate = criteriaBuilder.like(searchByGraphicAdapter,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByGraphicAdapterPredicate);
				}
				if (ret.equals("battery")) {
					Expression<String> searchByBattery = root.get("battery");
					Predicate searchTitleByBatteryPredicate = criteriaBuilder.like(searchByBattery,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByBatteryPredicate);
				}
				if (ret.equals("price")) {
					Expression<BigDecimal> searchByPrice = root.get("price");
					Predicate searchTitleByPricePredicate = criteriaBuilder.lessThanOrEqualTo(searchByPrice,
							BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByPricePredicate);
				}

				else {
					return null;
				}
			}
		};
	}

	@Override
	public List<LaptopDTO> findAllLaptopsByPages(Pageable pageable) {
		Page<Laptop> laptopPage = laptopRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<Laptop> laptop = laptopPage.getContent();
		List<LaptopDTO> laptopDTOs = modelMapper.mapAll(laptop, LaptopDTO.class);
		return laptopDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String laptopId, int numberUrl) {
		boolean result = false;

		String imageUrl = cloudinaryService.uploadFile(file, "Laptops");

		Laptop laptop = laptopRepositiry.findByLaptopId(laptopId);

		if (imageUrl == null) {
			throw new LaptopServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (laptop == null) {
			throw new LaptopNotFountException(ApplicationConstants.LAPTOP_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			laptop.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			laptop.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			laptop.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			laptop.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			laptop.setImageUrl5(imageUrl);
			result = true;
			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		laptopRepositiry.save(laptop);

		return result;

	}

	@Override
	public List<LaptopDTO> findLaptopByUserId(String userId) {

		if (laptopRepositiry.existsByLaptopUserId(userRepositiry.findByUserId(userId).getId())) {
			List<Laptop> laptop = laptopRepositiry.findByLaptopUserId(userRepositiry.findByUserId(userId).getId());

			List<LaptopDTO> laptopDTOs = modelMapper.mapAll(laptop, LaptopDTO.class);
			return laptopDTOs;

		} else {
			throw new LaptopServiceException("Ноутбуків з таким користувачем не знайдено");
		}
	}

}
