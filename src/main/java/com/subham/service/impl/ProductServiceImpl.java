package com.subham.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subham.mapper.ProductMapper;
import com.subham.model.Category;
import com.subham.model.Product;
import com.subham.model.Store;
import com.subham.model.User;
import com.subham.payload.dto.ProductDTO;
import com.subham.repository.Categoryrepository;
import com.subham.repository.ProductRepository;
import com.subham.repository.StoreRepository;
import com.subham.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final StoreRepository storeRepository;
	private final Categoryrepository categoryrepository;
	

	@Override
	public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
		Store store= storeRepository.findById(
				productDTO.getStoreId()
				).orElseThrow(
						()->  new Exception("Store not found")
						);
		Category category=categoryrepository.findById(productDTO.getCategoryId()).orElseThrow(
				()-> new  Exception("category not found")
				);
		
		Product product= ProductMapper.toEntity(productDTO, store,category);
		 Product savedProduct= productRepository.save(product);
		return ProductMapper.toDto(savedProduct);
	}

	@Override
	public ProductDTO updatedProduct(Long id, ProductDTO productDTO, User user) throws Exception {
		Product product=productRepository.findById(id).orElseThrow(
				()-> new Exception("Product not Found")
				);
		
		
		
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setSku(productDTO.getSku());
		product.setImage(productDTO.getImage());
		product.setMrp(productDTO.getMrp());
		product.setSellingPrice(productDTO.getSellingPrice());
		product.setBrand(productDTO.getBrand());
		product.setUpdatedAt(LocalDateTime.now());
		
		if(productDTO.getCategoryId() != null) {
			Category  category=categoryrepository.findById(productDTO.getCategoryId()).orElseThrow(
					()-> new Exception("category not found")
				
					);
			
			product.setCategory(category);
		}
		
		Product savedProduct=productRepository.save(product);
		
		return ProductMapper.toDto(savedProduct);
	}

	@Override
	public void deleteProduct(Long id, User user) throws Exception {
		Product product=productRepository.findById(id).orElseThrow(
				()->  new Exception("Product Not found")
				);
		productRepository.delete(product);
		
	}

	@Override
	public List<ProductDTO> getProductsByStoreId(Long storeId) {
		
		List<Product> products=productRepository.findByStoreId(storeId);
		
		return products.stream()
				.map(ProductMapper:: toDto)
				.collect(Collectors.toList());
		
	}

	@Override
	public List<ProductDTO> searchByKeyWord(Long storeId, String keyword) {
    List<Product> products=productRepository.searchByKeyword(storeId, keyword);
		
		return products.stream()
				.map(ProductMapper:: toDto)
				.collect(Collectors.toList());
	}

}
