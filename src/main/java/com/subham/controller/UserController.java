package com.subham.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subham.exceptions.UserException;
import com.subham.mapper.UserMapper;
import com.subham.model.User;
import com.subham.payload.dto.UserDto;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile(
			@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.getUserFromJwtToken(jwt);
		
		return ResponseEntity.ok(UserMapper.toDTO(user));
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws UserException{
		
		User user=userService.getUserById(id);
		
		if(user == null) {
			throw new UserException("User not found");
		}
		
		return ResponseEntity.ok(UserMapper.toDTO(user));
		
		
	}

}
