package com.subham.service;

import java.util.List;

import com.subham.model.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Long id,Customer customer) throws Exception;
	
	void deleteCustomer(Long id)throws Exception;
	
	Customer getCustomer(Long id) throws Exception;
	
	List<Customer> getAllCustomer() throws Exception;
	
	List<Customer> searchCustomer(String Keyword)throws Exception;
	
	
	
	

}
