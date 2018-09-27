package rostyslav.popadynets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.reques.SigninRequest;
import rostyslav.popadynets.domain.response.SigninResponse;
import rostyslav.popadynets.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("signup")
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO user) {
		userService.addUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
		UserDTO u = userService.findUserByEmail(request.getEmail());
		String token = userService.signin(request.getEmail(), request.getPassword());
		String role = "";
		System.out.println(token + "\n" + request.getEmail() + "\n" + request.getPassword());
		
		if(token != null) {
			role = userService.findUserByEmail(request.getEmail()).getRole().toString();
			System.out.println("ROLE: " + role);
		}
		
		return new ResponseEntity<SigninResponse>(new SigninResponse(token, role, u), HttpStatus.OK);
	}
	
}
