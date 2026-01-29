package com.subham.payload.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ProductDTO {
	
	
	private long id;
	

	private String name;
	
	
	private String sku;
	
	private String description;
	
	private Double mrp;
	
	private Double sellingPrice;
	
	private String brand;
	
	private String image;
	
	private CategoryDTO category;
	
	private Long categoryId;
	
	private Long storeId;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
}
