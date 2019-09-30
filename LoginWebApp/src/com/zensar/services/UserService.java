package com.zensar.services;

import java.util.List;

import com.zensar.entities.User;
import com.zensar.exception.ServiceException;

public interface UserService {
	void addUser(User user) throws ServiceException;
	void updateUser(User user) throws ServiceException;
	void removeUser(User user) throws ServiceException;
	User findUserByUsername(String username) throws ServiceException; 
	List<User> findAllUsers() throws ServiceException;
	
	
	boolean validateUser(User user) throws ServiceException;
	
}
