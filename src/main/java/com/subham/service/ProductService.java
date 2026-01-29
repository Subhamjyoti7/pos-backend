package com.subham.service;

import java.util.List;

import com.subham.model.User;
import com.subham.payload.dto.ProductDTO;

public interface ProductService {
	
	ProductDTO createProduct(ProductDTO productDTO,User user ) throws Exception;
	
	ProductDTO updatedProduct(Long id ,ProductDTO productDTO,User user) throws Exception;
	
	void deleteProduct(Long id,User user) throws Exception;
	
	List<ProductDTO>  getProductsByStoreId(Long storeId);
	
	List<ProductDTO> searchByKeyWord(Long storeId,String keyword);
	
	
	

}
