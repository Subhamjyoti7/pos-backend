package com.subham.mapper;

import java.util.stream.Collectors;

import com.subham.model.Order;
import com.subham.payload.dto.OrderDTO;

public class OrderMapper {
	
	public static OrderDTO toDTO(Order order) {
		return OrderDTO.builder()
				.id(order.getId())
				.totlAmount(order.getTotlAmount())
				.branchId(order.getBranch().getId())
				.cashier(UserMapper.toDTO(order.getCashier()))
				.customer(order.getCustomer())
				.paymentType(order.getPaymentType())
				.createdAt(order.getCreatedAt())
				.items(order.getItems().stream().map(OrderItemMapper :: toDTO ).collect(Collectors.toList()))
				.build();
				
				
	}

}
