package com.subham.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.subham.configuration.JwtProvider;
import com.subham.exceptions.UserException;
import com.subham.model.User;
import com.subham.repository.UserRepository;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final  JwtProvider jwtProvider;

	@Override
	public User getUserFromJwtToken(String token) throws UserException {
		String  email=jwtProvider.getEmailFromToken(token);
		User user=userRepository.findByEmail(email);
		if(user== null) {
			throw new UserException("Invalid token");
		}
		
		return user;
	}

	@Override
	public User getCurrentUser() throws UserException {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		if(user== null) {
			throw new UserException("User Not found");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		User user=userRepository.findByEmail(email);
		if(user== null) {
			throw new UserException("User Not found");
		}
		return user;
	}

	@Override
	public User getUserById(long id) {
	    return userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}


	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	

}
