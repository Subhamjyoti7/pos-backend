package com.subham.payload.dto;

import java.time.LocalDateTime;



import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class InventryDTO {
	
	
	private Long  id;

	private BranchDTO branch;
	
	private Long branchId;
	
	private Long productId;
	
	
	private ProductDTO product;
	
	private Integer quantity;
	
	private LocalDateTime lastUpdated;

}
