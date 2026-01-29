package com.subham.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.subham.domain.PaymentType;
import com.subham.model.Branch;
import com.subham.model.Customer;
import com.subham.model.OrderItem;
import com.subham.model.User;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class OrderDTO {
	
	private Long id;
	
	private Double totlAmount;
	
	private LocalDateTime createdAt;
	
	private Long branchId;
	
	private Long customerId;
	
	
	private BranchDTO branch;
	

	private UserDto cashier;
	
	private PaymentType paymentType;
	

	private Customer customer;
	
	private List<OrderItemDTO> items;

}
