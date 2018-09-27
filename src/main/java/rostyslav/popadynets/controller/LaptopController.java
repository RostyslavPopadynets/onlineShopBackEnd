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

import rostyslav.popadynets.domain.LaptopDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.LaptopFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.LaptopService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("laptop")
public class LaptopController {

	@Autowired
	public UserService userService;

	@Autowired
	public LaptopService laptopService;

	@Autowired
	public UserRepository userRepository;

	@PostMapping // @ModelAttribute
	public ResponseEntity<String> addLaptop(@RequestBody LaptopDTO laptopDTO) {

		UserDTO user = UserDTO.builder().firstName(laptopDTO.getLaptopUser().getFirstName())
				.lastName(laptopDTO.getLaptopUser().getLastName()).email(laptopDTO.getLaptopUser().getEmail())
				.password(laptopDTO.getLaptopUser().getPassword()).build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());

		if (existUser) {
			laptopDTO.setLaptopUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				System.out.println(user);
				userService.addUser(user);
				laptopDTO.setLaptopUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}

		try {
			laptopService.addLaptop(laptopDTO);
		} catch (Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження ноутбука.");
		}

		return new ResponseEntity<>(laptopDTO.getLaptopId(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<LaptopDTO>> getAllLaptops() {
		return new ResponseEntity<List<LaptopDTO>>(laptopService.findAllLaptops(), HttpStatus.OK);
	}

	@PostMapping("image/{imageNumber}/{laptopId}")
	public ResponseEntity<String> uploadImage(@PathVariable("laptopId") String laptopId,
			@PathVariable("imageNumber") String imageNumber,
			@RequestParam("image") MultipartFile file) {
		String res = "";
		try {
			boolean succ = laptopService.uploadImage(file, laptopId, Integer.parseInt(imageNumber));
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
	
	@GetMapping("/{laptopId}")
	public ResponseEntity<LaptopDTO> getLaptopById(@PathVariable("laptopId") String laptopId) {
		LaptopDTO laptop = laptopService.findLaptopById(laptopId);
		if (laptop == null) {
			System.err.println("Ноутбук з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LaptopDTO>(laptop, HttpStatus.OK);
	}

	@PutMapping("/{laptopId}")
	public ResponseEntity<Void> updateLaptop(@PathVariable("laptopId") String laptopId,
			@RequestBody LaptopDTO laptop
			){
		LaptopDTO laptopNew = laptopService.findLaptopById(laptopId);
		if (laptopNew != null) {
			
			laptopNew.setManufacturer(laptop.getManufacturer());
			laptopNew.setModel(laptop.getModel());
			laptopNew.setProcessor(laptop.getProcessor());
			laptopNew.setDiagonalScreen(laptop.getDiagonalScreen());
			laptopNew.setRam(laptop.getRam());
			laptopNew.setDriveCapacity(laptop.getDriveCapacity());
			laptopNew.setColor(laptop.getColor());
			laptopNew.setGraphicAdapter(laptop.getGraphicAdapter());
			laptopNew.setBattery(laptop.getBattery());
			laptopNew.setAdditional(laptop.getAdditional());
			laptopNew.setDescription(laptop.getDescription());
			laptopNew.setWeight(laptop.getWeight());
			laptopNew.setPrice(laptop.getPrice());
			laptopNew.setDimensionsWidth(laptop.getDimensionsWidth());
			laptopNew.setDimensionsHeight(laptop.getDimensionsHeight());
			laptopNew.setDimensionsLength(laptop.getDimensionsLength());

			laptopService.updateLaptop(laptopNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<LaptopDTO>> findAllLaptopsBySearch(LaptopFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<LaptopDTO> laptops = laptopService.findAllLaptopsBySpecification(filter, ret);
		
		if(laptops.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<LaptopDTO>>(laptops, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<LaptopDTO>> getLaptopsByUserId(@PathVariable("userId") String userId) {
		List<LaptopDTO> laptops = laptopService.findLaptopByUserId(userId);
		return new ResponseEntity<List<LaptopDTO>>(laptops, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<LaptopDTO>> findLaptopByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<LaptopDTO> laptops = laptopService.findAllLaptopsByPages(pageable);
		return new ResponseEntity<List<LaptopDTO>>(laptops, HttpStatus.OK);
	}
		
	@DeleteMapping("/{laptopId}")
	public ResponseEntity<Void> deleteLaptop(@PathVariable("laptopId") String laptopId) {
		LaptopDTO laptopNew = laptopService.findLaptopById(laptopId);
		if (laptopNew != null) {
			laptopService.deleteLaptop(laptopNew.getLaptopId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
