package com.lpu.Momento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserUserDetailsService userService;
	private final AuthService authService;
	

	public AuthController(UserUserDetailsService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignupDto signupDto) throws UserException {
		User user = userService.registerUser(signupDto);
		return  ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		String token = authService.login(loginDto);
		return ResponseEntity.ok(token);
	}
	

}
