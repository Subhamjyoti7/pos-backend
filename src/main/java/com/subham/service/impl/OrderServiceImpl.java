package com.subham.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subham.domain.OrderStatus;
import com.subham.domain.PaymentType;
import com.subham.mapper.OrderMapper;
import com.subham.model.Branch;
import com.subham.model.Order;
import com.subham.model.OrderItem;
import com.subham.model.Product;
import com.subham.model.User;
import com.subham.payload.dto.OrderDTO;
import com.subham.repository.OrderItemRepository;
import com.subham.repository.OrderRepository;
import com.subham.repository.ProductRepository;
import com.subham.service.OrderService;
import com.subham.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final UserService userService;
	
	private final ProductRepository productRepository;
	
	private final OrderRepository orderRepository;
	
	private final OrderItemRepository orderItemRepository;

	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {
		User cashier=userService.getCurrentUser();
		
		Branch branch=cashier.getBranch();
		
		if(branch == null) {
			throw new Exception("casier's branch not found");
			
		}
		
		Order order=Order.builder()
				.branch(branch)
				.cashier(cashier)
				.customer(orderDTO.getCustomer())
				.paymentType(orderDTO.getPaymentType())
				.build();
		
		List<OrderItem> orderItems= orderDTO.getItems().stream().map(
				itemDto ->{ Product product=productRepository.findById(itemDto.getProductId())
							.orElseThrow(()-> new  EntityNotFoundException("Product not found"));
					
	   return  OrderItem.builder()
							.product(product)
							.quantity(itemDto.getQuantity())
							.price(product.getSellingPrice()*itemDto.getQuantity())
							.order(order)
							.build();
	  // return orderItemRepository.save(orderItem);
//	   return orderItem;
				}
				).toList();
		
		double total=orderItems.stream().mapToDouble(
				OrderItem :: getPrice
				).sum();
		
		order.setTotlAmount(total);
				
	    order.setItems(orderItems);
				
		Order savedOrder=orderRepository.save(order);
		
		return OrderMapper.toDTO(savedOrder);
	}

	@Override
	public OrderDTO getOrderById(Long id) throws Exception {
		
		return orderRepository.findById(id)
				.map(OrderMapper:: toDTO)
				.orElseThrow(
				()-> new Exception("order not found with id"+ id));
	}

	@Override
	public List<OrderDTO> getOtrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType,
			OrderStatus status) throws Exception {
		
		return orderRepository.findByBranchId(branchId).stream()
				.filter(order -> customerId== null || 
				(order.getCustomer()!= null && order.getCustomer().getId().equals(customerId)) )
				.filter(order -> cashierId == null ||
				order.getCashier()!=null && 
				order.getCashier().getId().equals(cashierId)) 
				
				.filter(order-> paymentType ==null||
				order.getPaymentType()== paymentType)
				
				.map(OrderMapper :: toDTO).collect(Collectors.toList());
				
	}

	@Override
	public List<OrderDTO> getOrderByCashier(Long cashierId) {
		
		return orderRepository.findByCashierId(cashierId).stream()
				.map(OrderMapper :: toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(Long id) throws Exception {
		Order order=orderRepository.findById(id).orElseThrow(
				()-> new Exception("Order not found with id"+id)
				);
		
		orderRepository.delete(order);
		
	}

	@Override
	public List<OrderDTO> getTodayOrderByBranch(Long branchId) throws Exception {
		LocalDate today=LocalDate.now();
		
		LocalDateTime  start=today.atStartOfDay();
		
		LocalDateTime end=today.plusDays(1).atStartOfDay();
		return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start, end)
				.stream().map(OrderMapper :: toDTO).collect(Collectors.toList())
				;
	}

	@Override
	public List<OrderDTO> getOrderByCustomerId(Long customerId) throws Exception {
		
		return orderRepository.findByCustomerId(customerId)
				.stream().map(OrderMapper:: toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
		
		return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).
				stream().map(OrderMapper :: toDTO)
				.collect(Collectors.toList());
	}

}
