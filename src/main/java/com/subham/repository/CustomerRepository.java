package com.subham.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subham.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String fullName,String email);
	

}
