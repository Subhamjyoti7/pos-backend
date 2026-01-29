package com.subham.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.subham.configuration.JwtProvider;
import com.subham.domain.UserRole;
import com.subham.exceptions.UserException;
import com.subham.mapper.UserMapper;
import com.subham.model.User;
import com.subham.payload.dto.UserDto;
import com.subham.payload.response.AuthResponse;
import com.subham.repository.UserRepository;
import com.subham.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutheServiceImpl implements AuthService {
	
	private  final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtProvider jwtProvider;
	
	private final CustomeUserImplemantation customeUserImplemantation;
	

	@Override
	public AuthResponse signup(UserDto userDto) throws UserException {
		User user= userRepository.findByEmail(userDto.getEmail());
		
		if(user != null) {
			throw new UserException("emial id already registered");
		}
		if(userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
			throw new UserException("role admin is not allowed");
		}
		
		User newuser= new User();
		newuser.setEmail(userDto.getEmail());
		newuser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		newuser.setRole(userDto.getRole());
		newuser.setFullname(userDto.getFullname());
		newuser.setPhone(userDto.getPhone());
		newuser.setLastLogin(LocalDateTime.now());
		newuser.setCreatedAt(LocalDateTime.now());
		
		
		newuser.setUpdatedAt(LocalDateTime.now());
		
		User saveduser=userRepository.save(newuser);
		
		Authentication authentication= new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt= jwtProvider.generatetoken(authentication);
		
		AuthResponse authResponse= new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Registered Successfully");
		authResponse.setUser(UserMapper.toDTO(saveduser));
		
		
		
		return authResponse;
	}

	@Override
	public AuthResponse login(UserDto userDto) throws UserException {
		String email=userDto.getEmail();
		String password=userDto.getPassword();
		Authentication authentication= authenticate(email,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Collection<? extends GrantedAuthority>  authorities= authentication.getAuthorities();
		
		String role= authorities.iterator().next().getAuthority();
		
		String  jwt=jwtProvider.generatetoken(authentication);
		
		User user=userRepository.findByEmail(email);
		
		user.setLastLogin(LocalDateTime.now());
		
		userRepository.save(user);
		
		AuthResponse authResponse= new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login Successfully");
		authResponse.setUser(UserMapper.toDTO(user));
		
		
		
		return authResponse;
	}

	private Authentication authenticate(String email, String password) throws UserException {
		
		UserDetails userDetails= customeUserImplemantation.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new UserException("email id doesn't exist"+email);
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new UserException("Passoword doesn't match");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities()) ;
	}

}
