package com.subham.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subham.domain.StoreStatus;
import com.subham.exceptions.UserException;
import com.subham.mapper.StoreMapper;
import com.subham.model.Store;
import com.subham.model.User;
import com.subham.payload.dto.StoreDTO;
import com.subham.payload.response.ApiResponse;
import com.subham.service.StoreService;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
	
	private final StoreService storeService;
	private final UserService userService;
	
	
	@PostMapping()
	public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO,
			@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.getUserFromJwtToken(jwt);
		return ResponseEntity.ok(storeService.createStore(storeDTO, user));
		}
	
	
	
	@GetMapping()
	public ResponseEntity<List<StoreDTO>> GetAllStore(
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		return ResponseEntity.ok(storeService.getAllStore());
	}
	
	@GetMapping("/admin")
	public ResponseEntity<StoreDTO> GetStoreByAdmin(
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
	}
	
	@GetMapping("/employee")
	public ResponseEntity<StoreDTO> GetStoreByEmployee(
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		return ResponseEntity.ok(storeService.getStoreByEmployee());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id,
			@RequestBody StoreDTO storeDTO) throws Exception{
		
		return  ResponseEntity.ok(storeService.updaStore(id, storeDTO));
	}
	
	@PutMapping("/{id}/moderate")
	public ResponseEntity<StoreDTO> moderateStore(@PathVariable Long id,
			@RequestParam StoreStatus status,
			@RequestBody StoreDTO storeDTO) throws Exception{
		
		return  ResponseEntity.ok(storeService.moderateStore(id, status));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDTO> GetStoreById(@PathVariable  Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		return ResponseEntity.ok(storeService.getStoreById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception{
		
		storeService.deleteStore(id);
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage(" Store deleted Successfully");
		
		return  ResponseEntity.ok(apiResponse);
	}
	
	

}
