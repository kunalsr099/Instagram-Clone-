package com.lpu.Momento;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passswordEncoder;
	

	public UserUserDetailsService(UserRepository userRepository, PasswordEncoder passswordEncoder) {
		this.userRepository = userRepository;
		this.passswordEncoder = passswordEncoder;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return  userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
	}
	
	
    public User registerUser(SignupDto signupDto) throws UserException {
        Optional<User> isEmailExist = userRepository.findByEmail(signupDto.getEmail());
        if (isEmailExist.isPresent()) {
            throw new UserException("Email already exists");
        }

        Optional<User> isUsernameExist = userRepository.findByUsername(signupDto.getUsername());
        if (isUsernameExist.isPresent()) {
            throw new UserException("This username is already taken");
        }

        if (signupDto.getEmail() == null || signupDto.getPassword() == null ||
        		signupDto.getUsername() == null || signupDto.getName() == null) {
            throw new UserException("All fields are mandatory");
        }

        User newUser = new User();
        newUser.setEmail(signupDto.getEmail());
        newUser.setName(signupDto.getName());
        newUser.setPassword(passswordEncoder.encode(signupDto.getPassword()));
        //newUser.setPassword(signupDto.getPassword());
        newUser.setUsername(signupDto.getUsername());

        return userRepository.save(newUser);
    }


}
