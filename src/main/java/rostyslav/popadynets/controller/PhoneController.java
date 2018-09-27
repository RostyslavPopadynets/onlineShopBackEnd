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

import rostyslav.popadynets.domain.PhoneDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.PhoneFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.PhoneService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("smartphone")
public class PhoneController {

	@Autowired
	public UserService userService;

	@Autowired
	public PhoneService phoneService;

	@Autowired
	public UserRepository userRepository;

	@PostMapping // @ModelAttribute
	public ResponseEntity<String> addPhone(@RequestBody PhoneDTO phoneDTO) {

		UserDTO user = UserDTO.builder().firstName(phoneDTO.getPhoneUser().getFirstName())
				.lastName(phoneDTO.getPhoneUser().getLastName())
				.email(phoneDTO.getPhoneUser().getEmail())
				.password(phoneDTO.getPhoneUser().getPassword()).build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());

		if (existUser) {
			phoneDTO.setPhoneUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				userService.addUser(user);
				phoneDTO.setPhoneUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}

		try {
			phoneService.addPhone(phoneDTO);
		} catch (Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження смартфона.");
		}
		return new ResponseEntity<>(phoneDTO.getPhoneId(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PhoneDTO>> getAllPhones() {
		return new ResponseEntity<List<PhoneDTO>>(phoneService.findAllPhones(), HttpStatus.OK);
	}

	@PostMapping("image/{imageNumber}/{phoneId}")
	public ResponseEntity<String> uploadImage(@PathVariable("phoneId") String phoneId,
			@PathVariable("imageNumber") String imageNumber,
			@RequestParam("image") MultipartFile file) {
		String res = "";
		try {
			boolean succ = phoneService.uploadImage(file, phoneId, Integer.parseInt(imageNumber));
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
	
	@GetMapping("/{phoneId}")
	public ResponseEntity<PhoneDTO> getPhoneById(@PathVariable("phoneId") String phoneId) {
		PhoneDTO phone = phoneService.findPhoneById(phoneId);
		if (phone == null) {
			System.err.println("Смартфон з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PhoneDTO>(phone, HttpStatus.OK);
	}

	@PutMapping("/{phoneId}")
	public ResponseEntity<Void> updatePhone(@PathVariable("phoneId") String phoneId,
			@RequestBody PhoneDTO phone
			){
		PhoneDTO phoneNew = phoneService.findPhoneById(phoneId);
		if (phoneNew != null) {
			
			phoneNew.setManufacturer(phone.getManufacturer());
			phoneNew.setModel(phone.getModel());
			phoneNew.setDisplayDiagonal(phone.getDisplayDiagonal());
			phoneNew.setResolutionDisplay(phone.getResolutionDisplay());
			phoneNew.setRam(phone.getRam());;
			phoneNew.setMaxFlashMemory(phone.getMaxFlashMemory());
			phoneNew.setDriveCapacity(phone.getDriveCapacity());
			phoneNew.setNumberSim(phone.getNumberSim());
			phoneNew.setFrontalCamera(phone.getFrontalCamera());
			phoneNew.setMainCamera(phone.getMainCamera());
			phoneNew.setFlash(phone.isFlash());
			phoneNew.setAutofocus(phone.isAutofocus());
			phoneNew.setCoreNumber(phone.getCoreNumber());
			phoneNew.setFrequency(phone.getFrequency());
			phoneNew.setBatteryCapacity(phone.getBatteryCapacity());
			phoneNew.setWeight(phone.getWeight());
			phoneNew.setAdditional(phone.getAdditional());
			phoneNew.setDescription(phone.getDescription());
			phoneNew.setColor(phone.getColor());
			phoneNew.setPrice(phone.getPrice());
			phoneNew.setDimensionsHeight(phone.getDimensionsHeight());
			phoneNew.setDimensionsWidth(phone.getDimensionsWidth());
			phoneNew.setDimensionsLength(phone.getDimensionsLength());
			
			phoneService.updatePhone(phoneNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<PhoneDTO>> findAllPhonesBySearch(PhoneFilter filter,
			@PathVariable("nameSearch") String ret){
		List<PhoneDTO> phones = phoneService.findAllPhonesBySpecification(filter, ret);
		if(phones.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PhoneDTO>>(phones, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<PhoneDTO>> getPhonesByUserId(@PathVariable("userId") String userId) {
		System.out.println(userId);
		List<PhoneDTO> phones = phoneService.findPhoneByUserId(userId);
		System.out.println(phones);
		if(phones != null) {
			return new ResponseEntity<List<PhoneDTO>>(phones, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<PhoneDTO>> findPhonesByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		
		List<PhoneDTO> phones = phoneService.findAllPhonesByPages(pageable);
		return new ResponseEntity<List<PhoneDTO>>(phones, HttpStatus.OK);
	}
		
	@DeleteMapping("/{phoneId}")
	public ResponseEntity<Void> deletePhone(@PathVariable("phoneId") String phoneId) {
		PhoneDTO phoneNew = phoneService.findPhoneById(phoneId);
		if (phoneNew != null) {
			phoneService.deletePhone(phoneNew.getPhoneId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
