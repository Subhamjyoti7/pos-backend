package com.subham.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subham.mapper.InventryMapper;
import com.subham.model.Branch;
import com.subham.model.Inventry;
import com.subham.model.Product;
import com.subham.payload.dto.InventryDTO;
import com.subham.repository.BranchRepository;
import com.subham.repository.InventryRepository;
import com.subham.repository.ProductRepository;
import com.subham.service.InventryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventryServiceImpl implements InventryService {
	
	private final InventryRepository inventryRepository;
	
	private final BranchRepository branchRepository;
	
	private final ProductRepository productRepository;
	

	@Override
	public InventryDTO createInventry(InventryDTO inventryDTO) throws Exception {
		
		Branch branch =branchRepository.findById(inventryDTO.getBranchId())
				.orElseThrow(
				()-> new Exception("bracnch not exist"));
		
		Product product=productRepository.findById(inventryDTO.getProductId())
				.orElseThrow(
						()-> new Exception("Product not exist ...")
						);
		
		Inventry inventry= InventryMapper.toEntity(inventryDTO, branch, product);
		
		Inventry savedInventry=inventryRepository.save(inventry);
		
		return InventryMapper.toDTO(savedInventry);
	}	

	@Override
	public InventryDTO updateInventry(Long id,InventryDTO inventryDTO) throws Exception {
		
		Inventry inventry=inventryRepository.findById(id).orElseThrow(
				()-> new  Exception("inventry not found")
				);
		
		inventry.setQuantity(inventryDTO.getQuantity());
		
		Inventry updateInventry=inventryRepository.save(inventry);
		
		return InventryMapper.toDTO(updateInventry);


	
	}

	@Override
	public void deleteInventry(Long id) throws Exception {
		Inventry inventry=inventryRepository.findById(id).orElseThrow(
				()-> new  Exception("inventry not found")
				);
		inventryRepository.delete(inventry);
		
	}

	@Override
	public InventryDTO getInventryById(Long id) throws Exception {
		Inventry inventry=inventryRepository.findById(id).orElseThrow(
				()-> new  Exception("inventry not found")
				);
		return InventryMapper.toDTO(inventry);
	}

	@Override
	public InventryDTO getInventryByProductIdAndBranchId(Long productId, Long branchId) {
		Inventry inventry=inventryRepository.findByProductIdAndBranchId(productId, branchId);
		
		
		return InventryMapper.toDTO(inventry);
	}

	@Override
	public List<InventryDTO> getAllInventryByBranchId(Long branchId) {
		
		List<Inventry> inventries=inventryRepository.findByBranchId(branchId);
		return inventries.stream().map(
				InventryMapper::toDTO
				
				).collect(Collectors.toList());
				
		
		
	}

}
