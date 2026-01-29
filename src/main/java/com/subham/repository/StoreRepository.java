package com.subham.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subham.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Store findByStoreAdminId(Long adminId);
	
	
	

}
