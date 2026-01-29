package com.subham.payload.dto;

import java.time.LocalDateTime;

import com.subham.domain.StoreStatus;
import com.subham.model.StoreContact;
import com.subham.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class StoreDTO {

	
	private Long id;
	

	private String brand;
	

	private User storeAdmin;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private String description;
	
	private String storeType;
	
	private StoreStatus status;
	
	private StoreContact contact;
	
}
