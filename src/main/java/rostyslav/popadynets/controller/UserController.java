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

import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.UserFilter;
import rostyslav.popadynets.entity.Laptop;
import rostyslav.popadynets.entity.Monitor;
import rostyslav.popadynets.entity.PC;
import rostyslav.popadynets.entity.Phone;
import rostyslav.popadynets.entity.TV;
import rostyslav.popadynets.entity.Tablet;
import rostyslav.popadynets.entity.User;
import rostyslav.popadynets.entity.enums.UserRole;
import rostyslav.popadynets.repository.LaptopRepository;
import rostyslav.popadynets.repository.MonitorRepository;
import rostyslav.popadynets.repository.PCRepository;
import rostyslav.popadynets.repository.PhoneRepository;
import rostyslav.popadynets.repository.TVRepository;
import rostyslav.popadynets.repository.TabletRepository;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TVRepository tvRepository;
	
	@Autowired
	public PCRepository pcRepository;
	
	@Autowired
	public MonitorRepository monitorRepository;
	
	@Autowired
	public LaptopRepository laptopRepository;
	
	@Autowired
	public TabletRepository tabletRepository;
	
	@Autowired
	public PhoneRepository phoneRepository;
	
	private static String em = "";
	
	@GetMapping("check-email")
	public ResponseEntity<Boolean> checkUserByEmail(@RequestParam("email") String email) {
		em = email;
		System.out.println("Email: " + email);
		return new ResponseEntity<Boolean>(userService.existsUserByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("check-password")
	public ResponseEntity<Boolean> checkUserByPassword(@RequestParam("password") String password) {
		System.out.println("Password : " + password);
		boolean res = false;
		boolean m = userService.existsUserByEmail(em);
		if(m == true) {
			UserDTO uu = userService.findUserByEmail(em);
			if (uu.getPassword().equals(password)) {
				res = true;
			} 
		} else {
			boolean pn = userService.existsUserByPassword(password);
			res = pn;
		}
		
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
	
	@PostMapping				// @ModelAttribute
	public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
		
		if (userDTO != null) {
			userService.addUser(userDTO);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<List<UserDTO>>(userService.findAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") String userId) {
		UserDTO user = userService.findUserById(userId);
		if (user == null) {
			System.err.println("Користувача з таким id не знайдено!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@PutMapping("role/{userId}")
	public ResponseEntity<Void> updateUserRole(@PathVariable("userId") String userId){
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			if(user.getRole() == UserRole.ROLE_USER) {
				user.setRole(UserRole.ROLE_ADMIN);
			} else {
				user.setRole(UserRole.ROLE_USER);
			}
			userRepository.save(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId,
			@RequestBody UserDTO user
			){
		UserDTO userNew = userService.findUserById(userId);
		if (userNew != null) {
			user.setUserId(userId);
			userService.addUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/byEmail/{userEmail}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userEmail") String email) {
		UserDTO user = userService.findUserByEmail(email);
		if (user != null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{nameSearch}/search")
	public ResponseEntity<List<UserDTO>> findAllUserBySearch(UserFilter filter,
			@PathVariable("nameSearch") String ret){
		
		List<UserDTO> users = userService.findAllUsersBySpecification(filter, ret);
		
		if(users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<UserDTO>> findUsersByPage(
			@PageableDefault Pageable pageable // читаємо яка сторінка і скільки на ній елементів
			// ?page=1&size=3 - 4,5,6 виведеться бо сторінк
			) {
		List<UserDTO> users = userService.findAllUsersByPages(pageable);
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}
		
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
		UserDTO userNew = userService.findUserById(userId);

		if (monitorRepository.existsByMonitorUserId(userRepository.findByUserId(userId).getId())) {
			List<Monitor> monitor = monitorRepository.findByMonitorUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(Monitor m : monitor) {
				if(m != null) {
					monitorRepository.delete(m);
				}
			}	
		} 
		
		if (laptopRepository.existsByLaptopUserId(userRepository.findByUserId(userId).getId())) {
			List<Laptop> laptop = laptopRepository.findByLaptopUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(Laptop l : laptop) {
				if(l != null) {
					laptopRepository.delete(l);
				}
			}	
		} 
		
		if (tabletRepository.existsByTabletUserId(userRepository.findByUserId(userId).getId())) {
			List<Tablet> tablet = tabletRepository.findByTabletUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(Tablet t : tablet) {
				if(t != null) {
					tabletRepository.delete(t);
				}
			}	
		} 
		
		if (phoneRepository.existsByPhoneUserId(userRepository.findByUserId(userId).getId())) {
			List<Phone> phone = phoneRepository.findByPhoneUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(Phone p : phone) {
				if(p != null) {
					phoneRepository.delete(p);
				}
			}	
		} 
		
		if (tvRepository.existsByTvUserId(userRepository.findByUserId(userId).getId())) {
			List<TV> tv = tvRepository.findByTvUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(TV t : tv) {
				if(t != null) {
					tvRepository.delete(t);
				}
			}	
		}
		
		if (pcRepository.existsByPcUserId(userRepository.findByUserId(userId).getId())) {
			List<PC> pc = pcRepository.findByPcUserId(
					userRepository.findByUserId(userId).getId()
					);
			for(PC p : pc) {
				if(p != null) {
					pcRepository.delete(p);
				}
			}	
		} 
		
		if (userNew != null) {
			userService.deleteUser(userNew.getUserId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
