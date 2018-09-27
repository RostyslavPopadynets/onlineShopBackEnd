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
import rostyslav.popadynets.domain.PCDTO;
import rostyslav.popadynets.domain.filters.PCFilter;
import rostyslav.popadynets.entity.PC;
import rostyslav.popadynets.exceptions.PCServiceException;
import rostyslav.popadynets.exceptions.models.PCNotFountException;
import rostyslav.popadynets.repository.PCRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.PCService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class PCServiceImpl implements PCService {

	@Autowired
	private PCRepository pcRepositiry;

	@Autowired
	private UserRepository userRepositiry;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addPC(PCDTO PCDTO) {
		PCDTO.setPCId(stringUtils.generate());
		
		pcRepositiry.save(modelMapper.map(PCDTO, PC.class));
	}

	@Override
	public PCDTO findPCById(String pcid) {
		PC pc = null;
		try {
			pc = pcRepositiry.findByPcId(pcid);
		} catch (Exception e) {
			throw new PCNotFountException(ApplicationConstants.PC_NOT_FOUND_EXCEPTION);
		}
		PCDTO PCDTO = modelMapper.map(pc, PCDTO.class);
		return PCDTO;
	}

	@Override
	public List<PCDTO> findAllPCs() {
		List<PC> pc = pcRepositiry.findAll();
		List<PCDTO> PCDTOS = modelMapper.mapAll(pc, PCDTO.class);
		return PCDTOS;
	}

	@Override
	public PCDTO findByPCManufacturer(String manufacturer) {
		PC pc = pcRepositiry.findByManufacturer(manufacturer);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з таким виробником не знайдено");
		}
	}

	@Override
	public PCDTO findByPCModel(String model) {
		PC pc = pcRepositiry.findByModel(model);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою моделю не знайдено");
		}
	}

	@Override
	public PCDTO findByPCProcessor(String processor) {
		PC pc = pcRepositiry.findByProcessor(processor);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з таким процесором не знайдено");
		}
	}

	@Override
	public PCDTO findByPCRam(String ram) {
		PC pc = pcRepositiry.findByRam(ram);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою ram не знайдено");
		}
	}

	@Override
	public PCDTO findByPCOs(String os) {
		PC pc = pcRepositiry.findByOs(os);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою ос не знайдено");
		}
	}

	@Override
	public PCDTO findByPCDriveCapacity(String driveCapacity) {
		PC pc = pcRepositiry.findByDriveCapacity(driveCapacity);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою пам'яттю не знайдено");
		}
	}

	@Override
	public PCDTO findByPCColor(String color) {
		PC pc = pcRepositiry.findByColor(color);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з таким коляром не знайдено");
		}
	}

	@Override
	public PCDTO findByPCCooling(String cooling) {
		PC pc = pcRepositiry.findByCooling(cooling);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з таким охолодженням не знайдено");
		}
	}

	@Override
	public PCDTO findByPCGraphicAdapter(String graphicAdaptering) {
		PC pc = pcRepositiry.findByGraphicAdapter(graphicAdaptering);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою відеокартою не знайдено");
		}
	}

	@Override
	public PCDTO findByPCPrice(BigDecimal price) {
		PC pc = pcRepositiry.findByPrice(price);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою ціною не знайдено");
		}
	}

	@Override
	public PCDTO findByPCWeight(BigDecimal weight) {
		PC pc = pcRepositiry.findByWeight(weight);
		if (pc != null) {
			PCDTO pcDTO = modelMapper.map(pc, PCDTO.class);
			return pcDTO;
		} else {
			throw new PCServiceException("Комп'ютер з такою вагою не знайдено");
		}
	}

	@Override
	public void deletePC(String pcId) {
		PC pc = pcRepositiry.findByPcId(pcId);
		if (pc != null) {
			pcRepositiry.delete(pc);
		} else {
			throw new PCServiceException("Комп'ютер не знайдено");
		}
	}

	@Override
	public void updatePC(PCDTO pcDTO) {
		pcRepositiry.save(modelMapper.map(pcDTO, PC.class));
	}

	@Override
	public List<PCDTO> findAllPCsBySpecification(PCFilter filter, String ret) {
		return modelMapper.mapAll(pcRepositiry.findAll(getSpecification(filter, ret)), PCDTO.class);
	}

	private Specification<PC> getSpecification(PCFilter filter, String ret) {
		return new Specification<PC>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PC> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

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
				if (ret.equals("processor")) {
					Expression<String> searchByProcessor = root.get("processor");
					Predicate searchTitleByProcessorPredicate = criteriaBuilder.like(searchByProcessor,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByProcessorPredicate);
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

				if (ret.equals("cooling")) {
					Expression<String> searchByDriveCooling = root.get("cooling");
					Predicate searchTitleByCoolingPredicate = criteriaBuilder.like(searchByDriveCooling,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByCoolingPredicate);
				}
				if (ret.equals("graphicAdapter")) {
					Expression<String> searchByGraphicAdapter = root.get("graphicAdapter");
					Predicate searchTitleByGraphicAdapterPredicate = criteriaBuilder.like(searchByGraphicAdapter,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByGraphicAdapterPredicate);
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public List<PCDTO> findAllPCsByPages(Pageable pageable) {
		Page<PC> pcPage = pcRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<PC> pc = pcPage.getContent();
		List<PCDTO> pcDTOs = modelMapper.mapAll(pc, PCDTO.class);
		return pcDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String pcId, int numberUrl) {
		boolean result = false;

		String imageUrl = cloudinaryService.uploadFile(file, "PCs");

		PC pc = pcRepositiry.findByPcId(pcId);

		if (imageUrl == null) {
			throw new PCServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (pc == null) {
			throw new PCNotFountException(ApplicationConstants.PC_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			pc.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			pc.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			pc.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			pc.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			pc.setImageUrl5(imageUrl);

			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		pcRepositiry.save(pc);

		return result;
	}

	@Override
	public List<PCDTO> findPCByUserId(String userId) {
		if (pcRepositiry.existsByPcUserId(userRepositiry.findByUserId(userId).getId())) {

			List<PC> pc = pcRepositiry.findByPcUserId(userRepositiry.findByUserId(userId).getId());
			List<PCDTO> pcDTOs = modelMapper.mapAll(pc, PCDTO.class);

			return pcDTOs;

		} else {
			throw new PCServiceException("Комп'ютерів з таким користувачем не знайдено");
		}
	}

}
