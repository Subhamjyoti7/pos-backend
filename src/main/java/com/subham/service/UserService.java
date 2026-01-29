package com.subham.service;

import java.util.List;

import com.subham.exceptions.UserException;
import com.subham.model.User;

public interface UserService {
	
	User getUserFromJwtToken(String token) throws UserException;
	User getCurrentUser() throws UserException;
	User getUserByEmail(String email) throws UserException;
	User getUserById(long id) throws UserException;
	List<User> getAllUsers();
	
	

}
