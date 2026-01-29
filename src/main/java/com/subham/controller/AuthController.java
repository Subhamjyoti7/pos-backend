package com.subham.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subham.exceptions.UserException;
import com.subham.payload.dto.UserDto;
import com.subham.payload.response.AuthResponse;
import com.subham.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	// http://localhost:5000/auth/signup
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signupHandler(
			@RequestBody UserDto userDto) throws UserException{
		return ResponseEntity.ok(
				authService.signup(userDto));
		
	}
	
	// http://localhost:5000/auth/signup
		@PostMapping("/login")
		public ResponseEntity<AuthResponse> loginHandler(
				@RequestBody UserDto userDto) throws UserException{
			return ResponseEntity.ok(
					authService.login(userDto));
			
		}
	
	
	

}
