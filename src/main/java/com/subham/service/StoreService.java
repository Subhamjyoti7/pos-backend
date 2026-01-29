package com.subham.service;

import java.util.List;

import com.subham.domain.StoreStatus;
import com.subham.exceptions.UserException;
import com.subham.model.Store;
import com.subham.model.User;
import com.subham.payload.dto.StoreDTO;

public interface StoreService {
	
	StoreDTO createStore(StoreDTO storeDTO,User user);
	
	StoreDTO getStoreById(Long id) throws Exception;
	
	List<StoreDTO>  getAllStore();
	
	Store  getStoreByAdmin() throws UserException;
	
	StoreDTO  updaStore(Long id,StoreDTO storeDTO) throws UserException, Exception;
	
	void  deleteStore(Long id) throws UserException;
	
	StoreDTO getStoreByEmployee() throws UserException;
	
	StoreDTO moderateStore(Long id,StoreStatus status) throws Exception;
	
	
	
	
	

}
