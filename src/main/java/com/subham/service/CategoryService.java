package com.subham.service;

import java.util.List;

import com.subham.exceptions.UserException;
import com.subham.payload.dto.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO  createCategory(CategoryDTO categoryDTO) throws UserException, Exception;
	
	List<CategoryDTO> getCategoriesByStore(Long StoreId);
	
	CategoryDTO  updateCategory( Long id, CategoryDTO categoryDTO) throws Exception;
	
	void deleteCategory(Long id) throws Exception;
	

}
