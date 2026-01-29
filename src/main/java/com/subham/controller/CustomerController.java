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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subham.model.Customer;
import com.subham.payload.response.ApiResponse;
import com.subham.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping()
	public ResponseEntity<Customer> create(@RequestBody Customer customer){
	
	return ResponseEntity.ok(customerService.createCustomer(customer));
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@RequestBody Customer customer,
			@PathVariable Long id) throws Exception{
	
	return ResponseEntity.ok(customerService.updateCustomer(id, customer));
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception{
		customerService.deleteCustomer(id);
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage("customer deleted");
		
	
	return ResponseEntity.ok(apiResponse);
	
	}
	
	@GetMapping()
	public ResponseEntity<List<Customer>> getAll() throws Exception{
	
	return ResponseEntity.ok(customerService.getAllCustomer());
	
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Customer>> search(
			@RequestParam String  q
			) throws Exception{
	
	return ResponseEntity.ok(customerService.searchCustomer(q));
	
	}
	

	
	

}
