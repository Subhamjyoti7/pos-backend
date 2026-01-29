package com.subham.mapper;

import com.subham.model.User;
import com.subham.payload.dto.UserDto;

public class UserMapper {

	public static UserDto toDTO(User saveduser) {
		UserDto userDto= new UserDto();
		userDto.setId(saveduser.getId());
		userDto.setFullname(saveduser.getFullname());
		userDto.setEmail(saveduser.getEmail());
		userDto.setRole(saveduser.getRole());
		userDto.setCreatedAt(saveduser.getCreatedAt());
		userDto.setLastLogin(saveduser.getLastLogin());
		userDto.setUpdatedAt(saveduser.getUpdatedAt());
		userDto.setPhone(saveduser.getPhone());
		userDto.setStoreId(saveduser.getStore()!=null? saveduser.getStore().getId():null);
		userDto.setBranchId(saveduser.getBranch()!=null? saveduser.getBranch().getId():null);
		
		
		
		return userDto;
	}
	
	public static User toEntity(UserDto userDto) {
		User createdUser= new User();
		createdUser.setEmail(userDto.getEmail());
		createdUser.setFullname(userDto.getFullname());
		
		createdUser.setRole(userDto.getRole());
		createdUser.setCreatedAt(userDto.getCreatedAt());
		createdUser.setUpdatedAt(userDto.getUpdatedAt());
		createdUser.setLastLogin(userDto.getLastLogin());
		createdUser.setPhone(userDto.getPhone());
		createdUser.setPassword(userDto.getPassword());
		
		return createdUser;
		
		
		
		
		
	}

}
