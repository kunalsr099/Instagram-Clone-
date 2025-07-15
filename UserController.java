package com.lpu.Momento;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id ) throws UserException {
		User user = userService.findUserById(id);
		return  new ResponseEntity<User>(user, HttpStatus.OK);      
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> findUserByUsername(@PathVariable String username) throws UserException {
	    User user = userService.findUserByUsername(username);
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/batch")
	public ResponseEntity<List<User>> findUserByUserIds(@RequestParam List<Long> userIds) throws UserException {
	    List<User> users = userService.findUserByIds(userIds);
	    return new ResponseEntity<>(users, HttpStatus.OK);
	}

	
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query)  throws UserException{
		List<User> users = userService.searchUser(query);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	@PutMapping("/follow/{userId}")
	public ResponseEntity<MessageResponse> followUser(@PathVariable Long userId, @RequestHeader("Authorization") String token) throws UserException {
	User user = userService.findUserByToken(token);
	String res = userService.followUser(user.getId(), userId);
	MessageResponse message = new MessageResponse(res);
	return new ResponseEntity<MessageResponse>(message, HttpStatus.OK); 
	}
	
	@PutMapping("/unfollow/{userId}")
	public ResponseEntity<MessageResponse> unfollowUser(@PathVariable Long userId, @RequestHeader("Authorization") String token) throws UserException {
	User user = userService.findUserByToken(token);
	String res = userService.unfollowUser(user.getId(), userId);
	MessageResponse message = new MessageResponse(res);
	return new ResponseEntity<MessageResponse>(message, HttpStatus.OK); 
	}
	
	@GetMapping("/req")
	public ResponseEntity<User> findUserProfilebyToken(@RequestHeader("Authorization") String token) throws UserException {
		User user = userService.findUserByToken(token);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @RequestHeader("Authorization") String token) throws UserException { 
		User currentUser = userService.findUserByToken(token);
		User updatedUser = userService.updatedUser(user, currentUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
	}
	

		
	}
	
	
	


