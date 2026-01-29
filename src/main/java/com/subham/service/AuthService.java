package com.subham.service;

import com.subham.exceptions.UserException;
import com.subham.payload.dto.UserDto;
import com.subham.payload.response.AuthResponse;


public interface AuthService {
	
	AuthResponse signup(UserDto userDto) throws UserException ;
	
	AuthResponse login(UserDto userDto) throws UserException;
	

}
