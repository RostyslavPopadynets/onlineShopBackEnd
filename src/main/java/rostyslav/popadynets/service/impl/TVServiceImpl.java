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
import rostyslav.popadynets.domain.TVDTO;
import rostyslav.popadynets.domain.filters.TVFilter;
import rostyslav.popadynets.entity.TV;
import rostyslav.popadynets.exceptions.TVServiceException;
import rostyslav.popadynets.exceptions.models.TVNotFountException;
import rostyslav.popadynets.repository.TVRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.TVService;
import rostyslav.popadynets.service.cloudinary.CloudinaryService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class TVServiceImpl implements TVService {

	@Autowired
	private TVRepository tvRepositiry;

	@Autowired
	private UserRepository userRepositiry;
	
	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addTV(TVDTO tvDTO) {

		tvDTO.setTVId(stringUtils.generate());
		
		tvRepositiry.save(modelMapper.map(tvDTO, TV.class));
	}

	@Override
	public TVDTO findTVById(String TVid) {
		TV tv = null;
		try {
			tv = tvRepositiry.findByTvId(TVid);
		} catch (Exception e) {
			throw new TVNotFountException(ApplicationConstants.TV_NOT_FOUND_EXCEPTION);
		}
		TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
		return tvDTO;
	}

	@Override
	public List<TVDTO> findAllTVs() {
		List<TV> tvs = tvRepositiry.findAll();
		List<TVDTO> TVDTOs = modelMapper.mapAll(tvs, TVDTO.class);
		return TVDTOs;
	}

	@Override
	public TVDTO findTVByManufacturer(String manufacturer) {
		TV tv = tvRepositiry.findByManufacturer(manufacturer);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з таким виробником не знайдено");
		}
	}

	@Override
	public TVDTO findTVByModel(String model) {
		TV tv = tvRepositiry.findByModel(model);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такию моделю не знайдено");
		}
	}

	@Override
	public TVDTO findTVByDisplayDiagonal(BigDecimal displayDiagonal) {
		TV tv = tvRepositiry.findByDisplayDiagonal(displayDiagonal);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такою діагоналю не знайдено");
		}
	}

	@Override
	public TVDTO findTVByResolutionDisplay(String resolutionDisplay) {
		TV tv = tvRepositiry.findByResolutionDisplay(resolutionDisplay);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з таким розширенням не знайдено");
		}
	}

	@Override
	public TVDTO findTVBySmartPlatform(String smartPlatform) {
		TV tv = tvRepositiry.findBySmartPlatform(smartPlatform);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такию платформою не знайдено");
		}
	}

	@Override
	public TVDTO findTVByDisplayBrightness(String displayBrightness) {
		TV tv = tvRepositiry.findByDisplayBrightness(displayBrightness);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такою яскравістю не знайдено");
		}
	}

	@Override
	public TVDTO findTVByColor(String color) {
		TV tv = tvRepositiry.findByColor(color);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з таким коляром не знайдено");
		}
	}

	@Override
	public TVDTO findTVByHdr(boolean hdr) {
		TV tv = tvRepositiry.findByHdr(hdr);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з hdr не знайдено");
		}
	}

	@Override
	public TVDTO findTVByPrice(BigDecimal price) {
		TV tv = tvRepositiry.findByPrice(price);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такою ціною не знайдено");
		}
	}

	@Override
	public TVDTO findTVByWeight(BigDecimal weight) {
		TV tv = tvRepositiry.findByWeight(weight);
		if(tv != null) {
			TVDTO tvDTO = modelMapper.map(tv, TVDTO.class);
			return tvDTO;
		} else {
			throw new TVServiceException("Телевізора з такою вагою не знайдено");
		}
	}

	@Override
	public void deleteTV(String TVId) {
		TV tv = tvRepositiry.findByTvId(TVId);
		if (tv != null) {
			tvRepositiry.delete(tv);
		} else {
			throw new TVServiceException("Телевізора не знайдено");
		}
	}

	@Override
	public void updateTV(TVDTO tvDTO) {
		tvRepositiry.save(modelMapper.map(tvDTO, TV.class));
	}

	@Override
	public List<TVDTO> findAllTVsBySpecification(TVFilter filter, String ret) {
		return modelMapper.mapAll(tvRepositiry.findAll(getSpecification(filter, ret)), TVDTO.class);
	}

	private Specification<TV> getSpecification(TVFilter filter, String ret) {
		return new Specification<TV>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TV> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

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

				if (ret.equals("resolutionDisplay")) {
					Expression<String> searchByResolutionDisplay = root.get("resolutionDisplay");
					Predicate searchTitleByResolutionDisplayPredicate = criteriaBuilder.like(searchByResolutionDisplay,
							"%" + filter.getSearch() + "%");

					return criteriaBuilder.and(searchTitleByResolutionDisplayPredicate);
				}

				if (ret.equals("smartPlatform")) {
					Expression<String> searchBySmartPlatform = root.get("smartPlatform");
					Predicate searchTitleBySmartPlatformPredicate = criteriaBuilder.like(searchBySmartPlatform,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleBySmartPlatformPredicate);
				}

				if (ret.equals("displayBrightness")) {
					Expression<String> searchByDisplayBrightness = root.get("displayBrightness");
					Predicate searchTitleByDisplayBrightnessPredicate = criteriaBuilder.like(searchByDisplayBrightness,
							"%" + filter.getSearch() + "%");

					return criteriaBuilder.and(searchTitleByDisplayBrightnessPredicate);
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

				else {
					return null;
				}

			}
		};
	}

	@Override
	public List<TVDTO> findAllTVsByPages(Pageable pageable) {
		Page<TV> tvPage = tvRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<TV> tv = tvPage.getContent();
		
		List<TVDTO> tvDTOs = modelMapper.mapAll(tv, TVDTO.class);
		return tvDTOs;
	}

	@Override
	public boolean uploadImage(MultipartFile file, String tvID, int numberUrl) {
		boolean result = false;
		String imageUrl = cloudinaryService.uploadFile(file, "TVs");
		TV tv = tvRepositiry.findByTvId(tvID);

		if (imageUrl == null) {
			throw new TVServiceException(ApplicationConstants.FILE_ON_CLOUDINARY_NOT_FOUND_EXCEPTION);
		}

		if (tv == null) {
			throw new TVNotFountException(ApplicationConstants.TV_NOT_FOUND_EXCEPTION);
		}

		switch (numberUrl) {
		case 1:
			tv.setImageUrl1(imageUrl);
			result = true;
			break;
		case 2:
			tv.setImageUrl2(imageUrl);
			result = true;
			break;
		case 3:
			tv.setImageUrl3(imageUrl);
			result = true;
			break;
		case 4:
			tv.setImageUrl4(imageUrl);
			result = true;
			break;
		case 5:
			tv.setImageUrl5(imageUrl);
			result = true;
			break;
		default:
			System.err.println("numberUrl not 1-5 !!!");
		}

		tvRepositiry.save(tv);

		return result;
	}

	@Override
	public List<TVDTO> findTVByUserId(String userId) {

		if (tvRepositiry.existsByTvUserId(userRepositiry.findByUserId(userId).getId())) {
			List<TV> tv = tvRepositiry.findByTvUserId(userRepositiry.findByUserId(userId).getId());

			List<TVDTO> tvDTOs = modelMapper.mapAll(tv, TVDTO.class);
			return tvDTOs;

		} else {
			throw new TVServiceException("Телевзорів з таким користувачем не знайдено");
		}
	}

}
