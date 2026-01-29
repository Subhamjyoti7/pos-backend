package com.subham.payload.dto;




import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {
	
     private Long id;
	
	private Integer quantity;
	
	private  Double price;
	
	private Long productId;
	

	private ProductDTO product;

	private Long orderId;

}
