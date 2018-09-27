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
import rostyslav.popadynets.domain.MonitorDTO;
import rostyslav.popadynets.domain.filters.MonitorFilter;
import rostyslav.popadynets.entity.Monitor;
import rostyslav.popadynets.exceptions.MonitorServiceException;
import rostyslav.popadynets.exceptions.models.MonitorNotFountException;
import rostyslav.popadynets.repository.MonitorRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.MonitorService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class MonitorServiceImpl implements MonitorService {

	@Autowired
	private MonitorRepository monitorRepositiry;

	@Autowired
	private UserRepository userRepositiry;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addMonitor(MonitorDTO monitorDTO) {
		monitorDTO.setMonitorId(stringUtils.generate());
	
		monitorRepositiry.save(modelMapper.map(monitorDTO, Monitor.class));
	}

	@Override
	public MonitorDTO findMonitorById(String monitorid) {
		Monitor monitor = null;
		try {
			monitor = monitorRepositiry.findByMonitorId(monitorid);
		} catch (Exception e) {
			throw new MonitorNotFountException(ApplicationConstants.MONITOR_NOT_FOUND_EXCEPTION);
		}
		MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
		return monitorDTO;
	}

	@Override
	public List<MonitorDTO> findAllMonitors() {
		List<Monitor> monitor = monitorRepositiry.findAll();
		List<MonitorDTO> monitorDTOs = modelMapper.mapAll(monitor, MonitorDTO.class);
		return monitorDTOs;
	}

	@Override
	public MonitorDTO findByMonitorManufacturer(String manufacturer) {
		Monitor monitor = monitorRepositiry.findByManufacturer(manufacturer);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з таким виробником не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorModel(String model) {
		Monitor monitor = monitorRepositiry.findByModel(model);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з такою моделю не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorDisplayDiagonal(BigDecimal displayDiagonal) {
		Monitor monitor = monitorRepositiry.findByDisplayDiagonal(displayDiagonal);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з такою діагоналю не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorMaxResolutionDisplay(String maxResolutionDisplay) {
		Monitor monitor = monitorRepositiry.findByMaxResolutionDisplay(maxResolutionDisplay);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з таким розширенням не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorMatrixType(String matrixType) {
		Monitor monitor = monitorRepositiry.findByMatrixType(matrixType);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з такою матрицею не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorDisplayBrightness(String displayBrightness) {
		Monitor monitor = monitorRepositiry.findByDisplayBrightness(displayBrightness);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з яскравістю не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorInterfaces(String interfaces) {
		Monitor monitor = monitorRepositiry.findByInterfaces(interfaces);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з такими інтерфейсами не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorAttitudeParties(String attitudeParties) {
		Monitor monitor = monitorRepositiry.findByAttitudeParties(attitudeParties);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з таким відношенням сторін не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorColor(String color) {
		Monitor monitor = monitorRepositiry.findByColor(color);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з таким коляром не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorWebcam(boolean webcam) {
		Monitor monitor = monitorRepositiry.findByWebcam(webcam);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з веб-камерою не знайдено");
		}
	}

	@Override
	public MonitorDTO findByMonitorPrice(BigDecimal price) {
		Monitor monitor = monitorRepositiry.findByPrice(price);
		if (monitor != null) {
			MonitorDTO monitorDTO = modelMapper.map(monitor, MonitorDTO.class);
			return monitorDTO;
		} else {
			throw new MonitorServiceException("Монітор з такою ціною не знайдено");
		}
	}

	@Override
	public void deleteMonitor(String monitorId) {
		Monitor monitor = monitorRepositiry.findByMonitorId(monitorId);
		if (monitor != null) {
			monitorRepositiry.delete(monitor);
		} else {
			throw new MonitorServiceException("Монітор не знайдено");
		}
	}

	@Override
	public void updateMonitor(MonitorDTO monitorDTO) {
		monitorRepositiry.save(modelMapper.map(monitorDTO, Monitor.class));
	}

	@Override
	public List<MonitorDTO> findAllMonitorsBySpecification(MonitorFilter filter, String ret) {
		return modelMapper.mapAll(monitorRepositiry.findAll(getSpecification(filter, ret)), MonitorDTO.class);
	}

	private Specification<Monitor> getSpecification(MonitorFilter filter, String ret) {
		return new Specification<Monitor>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Monitor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

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
					Predicate searchTitleByDisplayDiagonalPredicate = criteriaBuilder.greaterThanOrEqualTo(
							searchByDisplayDiagonal, BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByDisplayDiagonalPredicate);
				}
				if (ret.equals("maxResolutionDisplay")) {
					Expression<String> searchByMaxResolutionDisplay = root.get("maxResolutionDisplay");
					Predicate searchTitleByMaxResolutionDisplayPredicate = criteriaBuilder
							.like(searchByMaxResolutionDisplay, "%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByMaxResolutionDisplayPredicate);
				}
				if (ret.equals("matrixType")) {
					Expression<String> searchByMatrixType = root.get("matrixType");
					Predicate searchTitleByMatrixTypePredicate = criteriaBuilder.like(searchByMatrixType,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByMatrixTypePredicate);
				}
				if (ret.equals("displayBrightness")) {
					Expression<String> searchByDisplayBrightness = root.get("displayBrightness");
					Predicate searchTitleByDisplayBrightnessPredicate = criteriaBuilder.like(searchByDisplayBrightness,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByDisplayBrightnessPredicate);
				}
				if (ret.equals("interfaces")) {
					Expression<String> searchByInterfaces = root.get("interfaces");
					Predicate searchTitleByInterfacesPredicate = criteriaBuilder.like(searchByInterfaces,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByInterfacesPredicate);
				}
				if (ret.equals("attitudeParties")) {
					Expression<String> searchByAttitudeParties = root.get("attitudeParties");
					Predicate searchTitleByAttitudePartiesPredicate = criteriaBuilder.like(searchByAttitudeParties,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByAttitudePartiesPredicate);
				}
				if (ret.equals("color")) {
					Expression<String> searchByColor = root.get("color");
					Predicate searchTitleByColorPredicate = criteriaBuilder.like(searchByColor,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByColorPredicate);
				}
				if (ret.equals("price")) {
					Expression<BigDecimal> searchByPrice = root.get("price");
					Predicate searchTitleByPricePredicate = criteriaBuilder.lessThanOrEqualTo(searchByPrice,
							BigDecimal.valueOf(Double.parseDouble(filter.getSearch())));
					return criteriaBuilder.and(searchTitleByPricePredicate);
				}
				if (ret.equals("webcam")) {
					Expression<Boolean> searchByDriveWebcam = root.get("webcam");
					Predicate searchTitleByWebcamPredicate = criteriaBuilder.equal(searchByDriveWebcam, true);
					return criteriaBuilder.and(searchTitleByWebcamPredicate);
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public List<MonitorDTO> findAllMonitorsByPages(Pageable pageable) {
		Page<Monitor> monitorPage = monitorRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<Monitor> monitor = monitorPage.getContent();
		List<MonitorDTO> monitorDTOs = modelMapper.mapAll(monitor, MonitorDTO.class);
		return monitorDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String monitorId, int numberUrl) {
		boolean result = false;

		String imageUrl = cloudinaryService.uploadFile(file, "Monitors");

		Monitor monitor = monitorRepositiry.findByMonitorId(monitorId);

		if (imageUrl == null) {
			throw new MonitorServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (monitor == null) {
			throw new MonitorNotFountException(ApplicationConstants.MONITOR_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			monitor.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			monitor.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			monitor.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			monitor.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			monitor.setImageUrl5(imageUrl);
			result = true;
			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		monitorRepositiry.save(monitor);

		return result;

	}

	@Override
	public List<MonitorDTO> findMonitorByUserId(String userId) {
		if (monitorRepositiry.existsByMonitorUserId(userRepositiry.findByUserId(userId).getId())) {

			List<Monitor> monitor = monitorRepositiry.findByMonitorUserId(userRepositiry.findByUserId(userId).getId());
			List<MonitorDTO> laptopDTOs = modelMapper.mapAll(monitor, MonitorDTO.class);

			return laptopDTOs;

		} else {
			throw new MonitorServiceException("Моніторів з таким користувачем не знайдено");
		}
	}

}
