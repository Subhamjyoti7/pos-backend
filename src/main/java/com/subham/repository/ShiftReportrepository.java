package com.subham.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subham.model.ShiftReport;
import com.subham.model.User;

public interface ShiftReportrepository extends JpaRepository<ShiftReport, Long> {
	
	List<ShiftReport> findByCashierId(Long id);
	
	List<ShiftReport> findByBranchId(Long id);
	
	Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);
	
	Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier,LocalDateTime start,LocalDateTime end);
	

}
