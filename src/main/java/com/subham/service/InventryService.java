package com.subham.service;

import java.util.List;

import com.subham.payload.dto.InventryDTO;

public interface InventryService {
	
	InventryDTO createInventry(InventryDTO inventryDTO) throws Exception;
	InventryDTO updateInventry(Long id,InventryDTO inventryDTO) throws Exception;
	void deleteInventry(Long id) throws Exception;
	InventryDTO getInventryById(Long id) throws Exception;
	InventryDTO getInventryByProductIdAndBranchId(Long productId,Long branchId);
	List<InventryDTO> getAllInventryByBranchId(Long branchId);
	

}
