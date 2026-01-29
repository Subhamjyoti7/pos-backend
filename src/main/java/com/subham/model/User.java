package com.subham.model;

import java.time.LocalDateTime;

import com.subham.domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String fullname;
	
	@Column(nullable = false, unique = true)
	@Email(message = "Email Should be valid ")
	private String email;
	
	private String phone;
	
	@ManyToOne
	private Store store;
	
	@ManyToOne
	private Branch branch;
	
	
	
	@Column(nullable = false)
	private UserRole role;
	
	private String password;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime lastLogin;
	
	
	
	
	

}
