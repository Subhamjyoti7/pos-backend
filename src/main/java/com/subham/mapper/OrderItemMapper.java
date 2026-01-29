package com.subham.mapper;

import com.subham.model.OrderItem;
import com.subham.payload.dto.OrderItemDTO;

public class OrderItemMapper {
	public static OrderItemDTO toDTO(OrderItem  item) {
		
		if(item==null) return null;
		
		return  OrderItemDTO.builder()
				.id(item.getId())
				.productId(item.getProduct().getId())
				.quantity(item.getQuantity())
				.price(item.getPrice())
				.product(ProductMapper.toDto(item.getProduct()))
				.build();
				
	}

}
