package com.subham.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subham.model.Inventry;

public interface InventryRepository extends JpaRepository<Inventry, Long> {
	
Inventry findByProductIdAndBranchId(long productid,Long branchId);
	
	List<Inventry> findByBranchId(Long branchId);
	
	
	

}
