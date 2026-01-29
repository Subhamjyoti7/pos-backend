package com.subham.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.subham.model.Category;

public interface Categoryrepository extends JpaRepository<Category, Long>{
	
	List<Category> findByStoreId(Long storeId);

}
