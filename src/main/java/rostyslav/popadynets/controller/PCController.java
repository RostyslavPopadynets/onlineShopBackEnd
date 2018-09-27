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

import rostyslav.popadynets.domain.PCDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.PCFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.PCService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("pc")
public class PCController {

	@Autowired
	public UserService userService;

	@Autowired
	public PCService pcService;

	@Autowired
	public UserRepository userRepository;

	@PostMapping // @ModelAttribute
	public ResponseEntity<String> addPC(@RequestBody PCDTO pcDTO) {

		UserDTO user = UserDTO.builder().firstName(pcDTO.getPcUser().getFirstName())
				.lastName(pcDTO.getPcUser().getLastName()).email(pcDTO.getPcUser().getEmail())
				.password(pcDTO.getPcUser().getPassword()).build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());

		if (existUser) {
			pcDTO.setPcUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				userService.addUser(user);
				pcDTO.setPcUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}

		try {
			pcService.addPC(pcDTO);
		} catch (Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження комп'ютера.");
		}
		return new ResponseEntity<>(pcDTO.getPCId(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PCDTO>> getAllPCs() {
		return new ResponseEntity<List<PCDTO>>(pcService.findAllPCs(), HttpStatus.OK);
	}

	@PostMapping("image/{imageNumber}/{pcId}")
	public ResponseEntity<String> uploadImage(@PathVariable("pcId") String pcId,
			@PathVariable("imageNumber") String imageNumber,
			@RequestParam("image") MultipartFile file
			) {
		String res = "";
		try {
			boolean succ = pcService.uploadImage(file, pcId, Integer.parseInt(imageNumber));
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

	@GetMapping("/{pcId}")
	public ResponseEntity<PCDTO> getPCById(@PathVariable("pcId") String pcId) {
		PCDTO pc = pcService.findPCById(pcId);
		if (pc == null) {
			System.err.println("Комп'ютер з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PCDTO>(pc, HttpStatus.OK);
	}

	@PutMapping("/{pcId}")
	public ResponseEntity<Void> updatePC(@PathVariable("pcId") String pcId,
			@RequestBody PCDTO pc
			){
		PCDTO pcNew = pcService.findPCById(pcId);
		if (pcNew != null) {
			
			pcNew.setManufacturer(pc.getManufacturer());
			pcNew.setModel(pc.getModel());
			pcNew.setProcessor(pc.getProcessor());
			pcNew.setRam(pc.getRam());;
			pcNew.setOs(pc.getOs());
			pcNew.setDriveCapacity(pc.getDriveCapacity());
			pcNew.setCooling(pc.getCooling());
			pcNew.setGraphicAdapter(pc.getGraphicAdapter());
			pcNew.setColor(pc.getColor());
			pcNew.setWeight(pc.getWeight());
			pcNew.setAdditional(pc.getAdditional());
			pcNew.setDescription(pc.getDescription());
			pcNew.setPrice(pc.getPrice());
			pcNew.setDimensionsHeight(pc.getDimensionsHeight());
			pcNew.setDimensionsWidth(pc.getDimensionsWidth());
			pcNew.setDimensionsLength(pc.getDimensionsLength());
			
			pcService.updatePC(pcNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<PCDTO>> findAllPCsBySearch(PCFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<PCDTO> pcs = pcService.findAllPCsBySpecification(filter, ret);
		
		if(pcs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PCDTO>>(pcs, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<PCDTO>> getPCsByUserId(@PathVariable("userId") String userId) {
		List<PCDTO> pcs = pcService.findPCByUserId(userId);
		return new ResponseEntity<List<PCDTO>>(pcs, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<PCDTO>> findPCByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<PCDTO> pcs = pcService.findAllPCsByPages(pageable);
		return new ResponseEntity<List<PCDTO>>(pcs, HttpStatus.OK);
	}
		
	@DeleteMapping("/{pcId}")
	public ResponseEntity<Void> deletePC(@PathVariable("pcId") String pcId) {
		PCDTO pcNew = pcService.findPCById(pcId);
		if (pcNew != null) {
			pcService.deletePC(pcNew.getPCId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
