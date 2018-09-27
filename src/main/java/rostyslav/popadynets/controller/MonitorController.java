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

import rostyslav.popadynets.domain.MonitorDTO;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.MonitorFilter;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.MonitorService;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("monitor")
public class MonitorController {

	@Autowired
	public UserService userService;

	@Autowired
	public MonitorService monitorService;

	@Autowired
	public UserRepository userRepository;

	@PostMapping // @ModelAttribute
	public ResponseEntity<String> addMonitor(@RequestBody MonitorDTO monitorDTO) {

		System.out.println(monitorDTO);

		UserDTO user = UserDTO.builder().firstName(monitorDTO.getMonitorUser().getFirstName())
				.lastName(monitorDTO.getMonitorUser().getLastName()).email(monitorDTO.getMonitorUser().getEmail())
				.password(monitorDTO.getMonitorUser().getPassword()).build();

		boolean existUser = userRepository.existsByEmail(user.getEmail());

		if (existUser) {
			monitorDTO.setMonitorUser(userRepository.findByEmail(user.getEmail()));
		} else {
			try {
				userService.addUser(user);
				monitorDTO.setMonitorUser(userRepository.findByEmail(user.getEmail()));
			} catch (Exception e) {
				System.err.println("Помилка збереження користувача.");
			}
		}

		try {
			monitorService.addMonitor(monitorDTO);
		} catch (Exception e) {
			userService.deleteUser(user.getUserId());
			System.err.println("Помилка збереження монітора.");
		}

		return new ResponseEntity<>(monitorDTO.getMonitorId(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<MonitorDTO>> getAllMonitors() {
		return new ResponseEntity<List<MonitorDTO>>(monitorService.findAllMonitors(), HttpStatus.OK);
	}

	@PostMapping("image/{imageNumber}/{monitorId}")
	public ResponseEntity<String> uploadImage(@PathVariable("monitorId") String monitorId,
			@PathVariable("imageNumber") String imageNumber,
			@RequestParam("image") MultipartFile file) {
		String res = "";
		try {
			boolean succ = monitorService.uploadImage(file, monitorId, Integer.parseInt(imageNumber));
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

	@GetMapping("/{monitorId}")
	public ResponseEntity<MonitorDTO> getMonitorById(@PathVariable("monitorId") String monitorId) {
		MonitorDTO monitor = monitorService.findMonitorById(monitorId);
		if (monitor == null) {
			System.err.println("Монітор з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MonitorDTO>(monitor, HttpStatus.OK);
	}

	@PutMapping("/{monitorId}")
	public ResponseEntity<Void> updateMonitor(@PathVariable("monitorId") String monitorId,
			@RequestBody MonitorDTO monitor
			){
		MonitorDTO monitorNew = monitorService.findMonitorById(monitorId);
		if (monitorNew != null) {
			
			monitorNew.setManufacturer(monitor.getManufacturer());
			monitorNew.setModel(monitor.getModel());
			monitorNew.setDisplayDiagonal(monitor.getDisplayDiagonal());
			monitorNew.setMaxResolutionDisplay(monitor.getMaxResolutionDisplay());;
			monitorNew.setMatrixType(monitor.getMatrixType());
			monitorNew.setDisplayBrightness(monitor.getDisplayBrightness());
			monitorNew.setInterfaces(monitor.getInterfaces());
			monitorNew.setAttitudeParties(monitor.getAttitudeParties());
			monitorNew.setColor(monitor.getColor());
			monitorNew.setWebcam(monitor.isWebcam());
			monitorNew.setAdditional(monitor.getAdditional());
			monitorNew.setDescription(monitor.getDescription());
			monitorNew.setPrice(monitor.getPrice());
			
			monitorService.updateMonitor(monitorNew);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<MonitorDTO>> findAllMonitorsBySearch(MonitorFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<MonitorDTO> monitors = monitorService.findAllMonitorsBySpecification(filter, ret);
		
		if(monitors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<MonitorDTO>>(monitors, HttpStatus.OK);
	}
	
	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<MonitorDTO>> getMonitorsByUserId(@PathVariable("userId") String userId) {
		List<MonitorDTO> monitors = monitorService.findMonitorByUserId(userId);
		return new ResponseEntity<List<MonitorDTO>>(monitors, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<MonitorDTO>> findMonitorByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<MonitorDTO> monitors = monitorService.findAllMonitorsByPages(pageable);
		return new ResponseEntity<List<MonitorDTO>>(monitors, HttpStatus.OK);
	}
		
	@DeleteMapping("/{monitorId}")
	public ResponseEntity<Void> deleteMonitor(@PathVariable("monitorId") String monitorId) {
		MonitorDTO monitorNew = monitorService.findMonitorById(monitorId);
		if (monitorNew != null) {
			monitorService.deleteMonitor(monitorNew.getMonitorId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
