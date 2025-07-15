package com.lpu.Momento;

import java.util.List;

public interface UserService {

    public User registerUser(User user) throws UserException;

    public User findUserById(Long id) throws UserException;
    public User findUserByToken(String token) throws UserException;
    
    public User findUserByUsername(String Username) throws UserException;
    public String followUser(Long reqUserId , Long followUserId) throws UserException;
    
    public String unfollowUser(Long reqUserId , Long followUserId) throws UserException;
    
    public List<User> findUserByIds(List<Long> userIds) throws UserException;
    
    public List<User> searchUser(String query) throws UserException;
    
    public User updatedUser(User updatedUser, User existingUser) throws UserException;
    
    

}
