package rostyslav.popadynets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rostyslav.popadynets.domain.TVDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.TVFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.TVService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("tv")
public class TVController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public TVService tvService;
	
	@Autowired
	public UserRepository userRepository;

	@PostMapping				// @ModelAttribute
	public ResponseEntity<String> addTV(@RequestBody TVDTO tvDTO) {

		UserDTO user = UserDTO.builder().firstName(tvDTO.getTvUser().getFirstName())
				  .lastName(tvDTO.getTvUser().getLastName())
				  .email(tvDTO.getTvUser().getEmail())
				  .password(tvDTO.getTvUser().getPassword())
				  .build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());
		
		if (existUser) {
			tvDTO.setTvUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				userService.addUser(user);
				tvDTO.setTvUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}
		
		try {
			tvService.addTV(tvDTO);
		} catch(Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження ТВ.");
		}
		
		return new ResponseEntity<>(tvDTO.getTVId(), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TVDTO>> getAllTVs() {
		return new ResponseEntity<List<TVDTO>>(tvService.findAllTVs(), HttpStatus.OK);
	}
	
	@PostMapping("image/{imageNumber}/{tvId}")
	public ResponseEntity<String> uploadImage(
			@PathVariable("tvId") String tvId,
			@PathVariable("imageNumber") String imageNumber,
			@RequestParam("image") MultipartFile file
			) {
		String res = "";
		try {
			boolean succ = tvService.uploadImage(file, tvId, Integer.parseInt(imageNumber));
			if(succ == true) {
				res = "good";
			} else if (succ == false) {
				res = "bad";
			}
		} catch (Exception e) {
			System.err.println("Помилка запису фото");
		}
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{tvId}")
	public ResponseEntity<TVDTO> getTVById(@PathVariable("tvId") String tvId) {
		TVDTO tv = tvService.findTVById(tvId);
		if (tv == null) {
			System.err.println("Телевізор з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TVDTO>(tv, HttpStatus.OK);
	}

	@PutMapping("/{tvId}")
	public ResponseEntity<Void> updateTV(@PathVariable("tvId") String tvId,
			@RequestBody TVDTO tv
			){
		TVDTO tvNew = tvService.findTVById(tvId);
		if (tvNew != null) {

			tvNew.setManufacturer(tv.getManufacturer());
			tvNew.setModel(tv.getModel());
			tvNew.setDisplayDiagonal(tv.getDisplayDiagonal());
			tvNew.setResolutionDisplay(tv.getResolutionDisplay());;
			tvNew.setSmartPlatform(tv.getSmartPlatform());
			tvNew.setDisplayBrightness(tv.getDisplayBrightness());
			tvNew.setColor(tv.getColor());
			tvNew.setHdr(tv.isHdr());
			tvNew.setAdditional(tv.getAdditional());
			tvNew.setDescription(tv.getDescription());
			tvNew.setPrice(tv.getPrice());
			tvNew.setWeight(tv.getWeight());
			tvNew.setDimensionsHeight(tv.getDimensionsHeight());
			tvNew.setDimensionsWidth(tv.getDimensionsWidth());
			tvNew.setDimensionsLength(tv.getDimensionsLength());
			
			tvService.updateTV(tvNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<TVDTO>> findAllTVsBySearch(TVFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<TVDTO> tvs = tvService.findAllTVsBySpecification(filter,ret);
		
		if(tvs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<TVDTO>>(tvs, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<TVDTO>> getTVByUserId(@PathVariable("userId") String userId) {
		List<TVDTO> tvs = tvService.findTVByUserId(userId);
		return new ResponseEntity<List<TVDTO>>(tvs, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<TVDTO>> findTVsByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<TVDTO> tvs = tvService.findAllTVsByPages(pageable);
		return new ResponseEntity<List<TVDTO>>(tvs, HttpStatus.OK);
	}
		
	@DeleteMapping("/{tvId}")
	public ResponseEntity<Void> deleteTV(@PathVariable("tvId") String tvId) {
		TVDTO tvNew = tvService.findTVById(tvId);
		if (tvNew != null) {
			tvService.deleteTV(tvNew.getTVId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
