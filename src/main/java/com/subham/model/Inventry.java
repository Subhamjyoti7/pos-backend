package com.subham.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class Inventry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long  id;
	
	@ManyToOne
	private Branch branch;
	@ManyToOne
	private Product product;
	
	@Column(nullable = false)
	private Integer quantity;
	
	private LocalDateTime lastUpdated;
	
	@PrePersist
	@PreUpdate
	private void  onUpdate() {
		lastUpdated=LocalDateTime.now();
		
	}
	
	
	
	

}
