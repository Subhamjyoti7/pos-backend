package com.subham.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subham.domain.UserRole;
import com.subham.mapper.CategoryMapper;
import com.subham.model.Category;
import com.subham.model.Store;
import com.subham.model.User;
import com.subham.payload.dto.CategoryDTO;
import com.subham.repository.Categoryrepository;
import com.subham.repository.StoreRepository;
import com.subham.service.CategoryService;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final Categoryrepository categoryrepository;
	
	private final UserService userService;
	
	private final StoreRepository storeRepository;
	
	
	
	
	@Override
	public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
		
		User user=userService.getCurrentUser();
		
		Store store=storeRepository.findById(dto.getStoreId()).orElseThrow(
				()->  new  Exception("Store not found")
				);
		Category category=Category.builder()
				.store(store)
				.name(dto.getName())
				.build();
		checkAuthorioty(user, category.getStore());
		

		return CategoryMapper.toDTO(categoryrepository.save(category));
	}

	@Override
	public List<CategoryDTO> getCategoriesByStore(Long StoreId) {
		List<Category> categories= categoryrepository.findByStoreId(StoreId);
		
		return  categories.stream()
				.map(
						CategoryMapper::toDTO
						).collect(Collectors.toList());
		
	
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {
		
		Category category=categoryrepository.findById(id).orElseThrow(
				() -> new  Exception("category not esxist")
				);
		User user= userService.getCurrentUser();
		
		
		category.setName(dto.getName());
		checkAuthorioty(user, category.getStore());
		
	
		return CategoryMapper.toDTO(categoryrepository.save(category));
	}

	@Override
	public void deleteCategory(Long id) throws Exception {
		Category category=categoryrepository.findById(id).orElseThrow(
				() -> new  Exception("category not esxist")
				);
		User user= userService.getCurrentUser();
		
		checkAuthorioty(user, category.getStore());
		
		categoryrepository.delete(category);
		
	}
	
	private void checkAuthorioty(User user,Store store) throws Exception {
		boolean isAdmin=user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
		
		boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
		
		boolean isSameStore=user.equals(store.getStoreAdmin());
		
		if(!(isAdmin && isSameStore) && !isManager) {
			
			throw new Exception("You don't have permission to manage this category");
			
			}
		
		
		
		
		
		
	}

}
