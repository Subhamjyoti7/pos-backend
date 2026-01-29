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

import com.subham.payload.dto.InventryDTO;
import com.subham.payload.response.ApiResponse;
import com.subham.service.InventryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventories")
public class InventryController {
	
	private final InventryService inventryService;
	
	
	@PostMapping()
	public  ResponseEntity<InventryDTO> create(
			@RequestBody InventryDTO inventryDTO
			) throws Exception{
		return ResponseEntity.ok(inventryService.createInventry(inventryDTO));
	}
	
	@PutMapping("/{id}")
	public  ResponseEntity<InventryDTO> update(
			@PathVariable Long id,
			@RequestBody InventryDTO inventryDTO
			) throws Exception{
		return ResponseEntity.ok(inventryService.updateInventry(id, inventryDTO));
	}
	

	@DeleteMapping("/{id}")
	public  ResponseEntity<ApiResponse> delete(
			@PathVariable Long id
			
			) throws Exception{
		
		inventryService.deleteInventry(id);
		
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage("inventry deleted successfully");
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/branch/{branchId}")
	public  ResponseEntity<List <InventryDTO>> getInventryByBranch(
			
			@PathVariable Long branchId
			) throws Exception{
		return ResponseEntity.ok(inventryService.getAllInventryByBranchId(branchId));
	}
	
	@GetMapping("/branch/{branchId}/product/{productId}")
	public  ResponseEntity<InventryDTO> getInventryByProductAndBranchId(
			@PathVariable Long branchId,
			@PathVariable Long productId
			) throws Exception{
		return ResponseEntity.ok(inventryService.getInventryByProductIdAndBranchId(productId, branchId));
	}

}
