package com.subham.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subham.domain.OrderStatus;
import com.subham.domain.PaymentType;
import com.subham.payload.dto.OrderDTO;
import com.subham.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception{
		
		return ResponseEntity.ok(orderService.createOrder(orderDTO) );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(
			@PathVariable Long id
			) throws Exception{
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	@GetMapping("/branch/{branchId}")
	public ResponseEntity<List<OrderDTO>> getOrderByBranch(
			@PathVariable Long branchId,
			@RequestParam(required = false) Long customerId,
			@RequestParam(required = false) Long cashierId,
			@RequestParam(required = false) PaymentType paymentType,
			@RequestParam(required = false) OrderStatus orderStatus
			
			
			) throws Exception{
		return ResponseEntity.ok(orderService.getOtrdersByBranch(branchId, customerId, cashierId, paymentType, orderStatus));
	}
	
	
	@GetMapping("/cashier/{id}")
	public ResponseEntity<List<OrderDTO>> getOrderByCashier(
			@PathVariable Long id
			) throws Exception{
		return ResponseEntity.ok(orderService.getOrderByCashier(id));
	}
	
	@GetMapping("/today/branch{id}")
	public ResponseEntity<List<OrderDTO>> getTodayOrder(
			@PathVariable Long id
			) throws Exception{
		return ResponseEntity.ok(orderService.getTodayOrderByBranch(id));
	}
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<List<OrderDTO>> getCustomersOrder(
			@PathVariable Long id
			) throws Exception{
		return ResponseEntity.ok(orderService.getOrderByCustomerId(id));
	}
	
	@GetMapping("/recent/{branchId}")
	public ResponseEntity<List<OrderDTO>> getRecentOrderOrder(
			@PathVariable Long idbranchId
			) throws Exception{
		return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(idbranchId));
	}
	
	
	
	

}
