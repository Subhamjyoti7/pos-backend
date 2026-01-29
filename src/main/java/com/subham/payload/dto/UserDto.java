package com.subham.payload.dto;

import java.time.LocalDateTime;

import com.subham.domain.UserRole;


import lombok.Data;

@Data
public class UserDto {
	

	private Long id;

	private String fullname;

	private String email;
	
	private String phone;
	

	private UserRole role;
	
	private String password;
	
	private Long branchId;
	
	private Long storeId;
	
	

	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime lastLogin;
	

}
