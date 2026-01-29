package com.subham.service;

import java.util.List;

import com.subham.domain.UserRole;
import com.subham.model.User;
import com.subham.payload.dto.UserDto;

public interface EmployeeService {
	
	UserDto createStoreEmployee(UserDto employee,Long storeId) throws Exception;
	UserDto createBranchEmployee(UserDto employee,Long branchId) throws Exception;

	User updateEmployee(long employeeId,UserDto employeeDetails) throws Exception;
	
	void deleteEmployee(Long employeeId) throws Exception;
	
	List<UserDto> findStoreEmployees(Long storeId,UserRole role) throws Exception;
	List<UserDto> findBranchEmployee(Long branchId,UserRole role) throws Exception;
	
	
	
	
	
	

}
