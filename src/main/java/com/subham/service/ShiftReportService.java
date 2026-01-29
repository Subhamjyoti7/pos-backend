package com.subham.service;

import java.time.LocalDateTime;
import java.util.List;

import com.subham.model.ShiftReport;
import com.subham.payload.dto.ShiftReportDTO;

public interface ShiftReportService {
	
	ShiftReportDTO startShift() throws Exception;
	
	ShiftReportDTO endShift(Long shiftReportId,LocalDateTime shiftEnd) throws Exception;
	
	ShiftReportDTO getShiftreportById(Long id) throws Exception;
	
	List<ShiftReportDTO> getAllShiftReports();
	
	List<ShiftReportDTO> getShiftReportByBranchId(Long branchId);
	
	List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId);
	
	ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception;
	
	ShiftReportDTO getShiftByCashierAndDate(Long cashierId,LocalDateTime date) throws Exception;
	
	

}
