package com.subham.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subham.exceptions.UserException;
import com.subham.model.Branch;
import com.subham.payload.dto.BranchDTO;
import com.subham.payload.response.ApiResponse;
import com.subham.service.BranchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
	
	private final BranchService branchService;
	
	@PostMapping()
	public ResponseEntity<BranchDTO> createBranch(
			@RequestBody BranchDTO branchDTO
			) throws UserException{
		BranchDTO createdBranch=branchService.createBranch(branchDTO);
		return ResponseEntity.ok(createdBranch);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchDTO> getBranchById(
			@PathVariable Long id
			) throws Exception{
		BranchDTO createdBranch=branchService.getBranchById(id);
		return ResponseEntity.ok(createdBranch);
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<List<BranchDTO>>getAllBranchesByStoreId(
			@PathVariable Long storeId
			) throws Exception{
		List<BranchDTO> createdBranch=branchService.getAllBranchesByStoreId(storeId);
		return ResponseEntity.ok(createdBranch);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<BranchDTO> updatedBranch(
			@PathVariable Long id,
			@RequestBody BranchDTO branchDTO
			) throws Exception{
		BranchDTO createdBranch=branchService.updateBranch(id,branchDTO);
		return ResponseEntity.ok(createdBranch);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBranch(
			@PathVariable Long id
			
			) throws Exception{
		branchService.deleteBranch(id);
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage("branch is deleted successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	
	
	
	
	
	
	
	

}
