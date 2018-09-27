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

import rostyslav.popadynets.domain.TabletDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.TabletFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.TabletService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("tablet")
public class TabletController {

	@Autowired
	public UserService userService;

	@Autowired
	public TabletService tabletService;

	@Autowired
	public UserRepository userRepository;

	@PostMapping // @ModelAttribute
	public ResponseEntity<String> addTablet(@RequestBody TabletDTO tabletDTO) {

		UserDTO user = UserDTO.builder().firstName(tabletDTO.getTabletUser().getFirstName())
				.lastName(tabletDTO.getTabletUser().getLastName()).email(tabletDTO.getTabletUser().getEmail())
				.password(tabletDTO.getTabletUser().getPassword()).build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());

		if (existUser) {
			tabletDTO.setTabletUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				userService.addUser(user);
				tabletDTO.setTabletUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}

		try {
			tabletService.addTablet(tabletDTO);
		} catch (Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження планшета.");
		}

		return new ResponseEntity<>(tabletDTO.getTabletId(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TabletDTO>> getAllTablets() {
		return new ResponseEntity<List<TabletDTO>>(tabletService.findAllTablets(), HttpStatus.OK);
	}

	@PostMapping("image/{imageNumber}/{tabletId}")
	public ResponseEntity<String> uploadImage(@PathVariable("tabletId") String tabletId,
			@RequestParam("image") MultipartFile file,
			@PathVariable("imageNumber") String imageNumber) {
		String res = "";
		try {
			boolean succ = tabletService.uploadImage(file, tabletId, Integer.parseInt(imageNumber));
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

	@GetMapping("/{tabletId}")
	public ResponseEntity<TabletDTO> getTabletById(@PathVariable("tabletId") String tabletId) {
		TabletDTO tablet = tabletService.findTabletById(tabletId);
		if (tablet == null) {
			System.err.println("Планшет з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TabletDTO>(tablet, HttpStatus.OK);
	}

	@PutMapping("/{tabletId}")
	public ResponseEntity<Void> updateTablet(@PathVariable("tabletId") String tabletId,
			@RequestBody TabletDTO tablet
			){
		TabletDTO tabletNew = tabletService.findTabletById(tabletId);
		if (tabletNew != null) {
	
			tabletNew.setManufacturer(tablet.getManufacturer());
			tabletNew.setModel(tablet.getModel());
			tabletNew.setDisplayDiagonal(tablet.getDisplayDiagonal());
			tabletNew.setResolutionDisplay(tablet.getResolutionDisplay());
			tabletNew.setRam(tablet.getRam());;
			tabletNew.setMaxFlashMemory(tablet.getMaxFlashMemory());
			tabletNew.setDriveCapacity(tablet.getDriveCapacity());
			tabletNew.setColor(tablet.getColor());
			tabletNew.setFrontalCamera(tablet.getFrontalCamera());
			tabletNew.setMainCamera(tablet.getMainCamera());
			tabletNew.setCoreNumber(tablet.getCoreNumber());
			tabletNew.setFrequency(tablet.getFrequency());
			tabletNew.setBatteryCapacity(tablet.getBatteryCapacity());
			tabletNew.setWeight(tablet.getWeight());
			tabletNew.setAdditional(tablet.getAdditional());
			tabletNew.setDescription(tablet.getDescription());
			tabletNew.setPrice(tablet.getPrice());
			tabletNew.setDimensionsHeight(tablet.getDimensionsHeight());
			tabletNew.setDimensionsWidth(tablet.getDimensionsWidth());
			tabletNew.setDimensionsLength(tablet.getDimensionsLength());
			
			tabletService.updateTablet(tabletNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<TabletDTO>> findAllTabletsBySearch(TabletFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<TabletDTO> tablets = tabletService.findAllTabletsBySpecification(filter, ret);
		
		if(tablets.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<TabletDTO>>(tablets, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<TabletDTO>> getTabletByUserId(@PathVariable("userId") String userId) {
		List<TabletDTO> tablets = tabletService.findTabletByUserId(userId);
		return new ResponseEntity<List<TabletDTO>>(tablets, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<TabletDTO>> findTabletsByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<TabletDTO> tablets = tabletService.findAllTabletsByPages(pageable);
		return new ResponseEntity<List<TabletDTO>>(tablets, HttpStatus.OK);
	}
		
	@DeleteMapping("/{tabletId}")
	public ResponseEntity<Void> deleteTablet(@PathVariable("tabletId") String tabletId) {
		TabletDTO tabletNew = tabletService.findTabletById(tabletId);
		if (tabletNew != null) {
			tabletService.deleteTablet(tabletNew.getTabletId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
