package com.subham.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.StoreManager;
import org.springframework.stereotype.Service;

import com.subham.domain.StoreStatus;
import com.subham.exceptions.UserException;
import com.subham.mapper.StoreMapper;
import com.subham.model.Store;
import com.subham.model.StoreContact;
import com.subham.model.User;
import com.subham.payload.dto.StoreDTO;
import com.subham.repository.StoreRepository;
import com.subham.service.StoreService;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
	
	private final  StoreRepository storeRepository ;
	
	private final UserService userService;
	
	

	@Override
	public StoreDTO createStore(StoreDTO storeDTO, User user) {
		Store store= StoreMapper.toEntity(storeDTO, user);
		return StoreMapper.toDto(storeRepository.save(store));
		
	}

	@Override
	public StoreDTO getStoreById(Long id) throws Exception {
		
		Store store= storeRepository.findById(id).orElseThrow(
				()-> new Exception("Store not ound"));
	
		return null;
	}

	@Override
	public List<StoreDTO> getAllStore() {
		List<Store> dtos= storeRepository.findAll();
		
		return dtos.stream().map(StoreMapper::toDto).collect(Collectors.toList());
	
		
	}

	@Override
	public Store getStoreByAdmin() throws UserException {
		User admin=userService.getCurrentUser();
		
	
		return storeRepository.findByStoreAdminId(admin.getId());
	}

	@Override
	public StoreDTO updaStore(Long id, StoreDTO storeDTO) throws Exception {
		User currenUser= userService.getCurrentUser();
		Store existing=storeRepository.findByStoreAdminId(currenUser.getId());
		
		if(existing == null) {
			throw new Exception("Store Not found");
			
		}
		existing.setBrand(storeDTO.getBrand());
		existing.setDescription(storeDTO.getDescription());
		if(storeDTO.getStoreType() != null) {
			existing.setStoreType(storeDTO.getStoreType());
		}
		
		if(storeDTO.getContact()!= null) {
			StoreContact contact=StoreContact.builder()
					.address(storeDTO.getContact().getAddress())
					.phone(storeDTO.getContact().getPhone())
					.emil(storeDTO.getContact().getEmil())
					.build();
			
			existing.setContact(contact);
			
		}
		
		Store updatedStore= storeRepository.save(existing);
		return StoreMapper.toDto(updatedStore);
	}

	@Override
	public void deleteStore(Long id) throws UserException {
		
		Store store= getStoreByAdmin();
		
		storeRepository.delete(store);
	}

	@Override
	public StoreDTO getStoreByEmployee() throws UserException {
		User currentUser= userService.getCurrentUser();
		
		if(currentUser == null) {
			throw new UserException("You don't have permission to access the store");
		}
		return StoreMapper.toDto(currentUser.getStore());
	}

	@Override
	public StoreDTO moderateStore(Long id, StoreStatus status) throws Exception {
		Store  store=storeRepository.findById(id).orElseThrow(
				() -> new Exception("Store not found"));
		store.setStatus(status);
		Store updateStore= storeRepository.save(store);
		
		
	
		return StoreMapper.toDto(updateStore);
	}

}
