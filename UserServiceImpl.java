package com.lpu.Momento;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist.isPresent()) {
            throw new UserException("Email already exists");
        }

        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if (isUsernameExist.isPresent()) {
            throw new UserException("This username is already taken");
        }

        if (user.getEmail() == null || user.getPassword() == null ||
            user.getUsername() == null || user.getName() == null) {
            throw new UserException("All fields are mandatory");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User does not exist with id: " + id));
    }

    @Override
    public User findUserByToken(String token) throws UserException {
    	if(token.startsWith("Bearer ")) {
    		token = token.substring(7);
    	}
    	Long id = jwtService.getIdFromToken(token);
    	return userRepository.findById(id)
    			.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    	
    }
        

    @Override
    public User findUserByUsername(String username) throws UserException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User not found with username: " + username));
    }

    @Override
    public String followUser(Long reqUserId, Long followUserId) throws UserException {
        if (reqUserId.equals(followUserId)) {
            throw new UserException("You cannot follow yourself");
        }

        User reqUser = userRepository.findById(reqUserId)
                .orElseThrow(() -> new UserException("User not found with id: " + reqUserId));
        User followUser = userRepository.findById(followUserId)
                .orElseThrow(() -> new UserException("User not found with id: " + followUserId));

        if (reqUser.getFollowing().contains(followUser)) {
            throw new UserException("You are already following " + followUser.getUsername());
        }

        reqUser.getFollowing().add(followUser);
        followUser.getFollower().add(reqUser);

        userRepository.save(reqUser);
        userRepository.save(followUser);

        return "You are now following " + followUser.getUsername();
    }

    @Override
    public String unfollowUser(Long reqUserId, Long followUserId) throws UserException {
        User reqUser = userRepository.findById(reqUserId)
                .orElseThrow(() -> new UserException("User not found with id: " + reqUserId));
        User followUser = userRepository.findById(followUserId)
                .orElseThrow(() -> new UserException("User not found with id: " + followUserId));

        reqUser.getFollowing().remove(followUser);
        followUser.getFollower().remove(reqUser);

        userRepository.save(reqUser);
        userRepository.save(followUser);

        return "You have unfollowed " + followUser.getUsername();
    }

    @Override
    public List<User> findUserByIds(List<Long> userIds) throws UserException {
        List<User> users = userRepository.findByIds(userIds);
        return users;
    }

    //@Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if (users.isEmpty()) {
            throw new UserException("No users found matching: " + query);
        }
        return users;
    }
    
    public User updatedUser(User updatedUser, User existingUser) throws UserException {
    	if(!updatedUser.getId().equals(existingUser.getId())) {
    		throw new UserException("You cannot update user");
    	}
    	
    	if(updatedUser.getEmail() != null) {
    		existingUser.setEmail(updatedUser.getEmail());
    		
    	}
    	if(updatedUser.getBio()!= null) {
    		existingUser.setBio(updatedUser.getBio());;
    		
    	}
    	
    	if(updatedUser.getName() != null) {
    		existingUser.setName(updatedUser.getName());
    		
    	}
    	
    	if(updatedUser.getUsername() != null) {
    		existingUser.setUsername(updatedUser.getUsername());;
    		
    	}
    	
    	if(updatedUser.getGender() != null) {
    		existingUser.setGender(updatedUser.getGender());;
    		
    	}
    	if(updatedUser.getMobile() != null) {
    		existingUser.setMobile(updatedUser.getMobile());;
    		
    	}
    	if(updatedUser.getImage() != null) {
    		existingUser.setImage(updatedUser.getImage());;
    		
    	}
    	if(updatedUser.getId().equals(existingUser.getId())) {
    		userRepository.save(existingUser);
    		
    	}
    	throw new UserException("you cannot Update user");
    }
    
}
