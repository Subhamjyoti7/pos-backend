package com.subham.service;

import java.util.List;

import com.subham.exceptions.UserException;
import com.subham.model.User;
import com.subham.payload.dto.BranchDTO;

public interface BranchService {
	
	BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
	
	BranchDTO updateBranch(Long id,BranchDTO branchDTO) throws Exception;
	
	void deleteBranch(Long id) throws Exception;
	
	List<BranchDTO> getAllBranchesByStoreId(Long storeId);
	
	BranchDTO getBranchById(Long id) throws Exception;
	
	
	
	

}
