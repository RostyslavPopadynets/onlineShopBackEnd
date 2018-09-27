package rostyslav.popadynets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rostyslav.popadynets.service.UserService;

@RestController
public class BaseController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
		userService.verifyAccount(token);
		return new ResponseEntity<String>("Аккаунт підтверджено", HttpStatus.OK);
	}
	
}
