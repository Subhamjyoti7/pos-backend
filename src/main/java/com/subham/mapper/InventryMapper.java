package com.subham.mapper;

import com.subham.model.Branch;
import com.subham.model.Inventry;
import com.subham.model.Product;
import com.subham.payload.dto.InventryDTO;

public class InventryMapper {
	public static InventryDTO toDTO(Inventry inventry) {
		return InventryDTO.builder()
				.id(inventry.getId())
				.branchId(inventry.getBranch().getId())
				.productId(inventry.getProduct().getId())
				.product(ProductMapper.toDto(inventry.getProduct()))
				.quantity(inventry.getQuantity())
				.build();
	}
	
	public static Inventry toEntity(InventryDTO inventryDTO,Branch branch,Product product) {
		return Inventry.builder()
				.branch(branch)
				.product(product)
				.quantity(inventryDTO.getQuantity())
				.build();
				
		
	}
	
	

}
