package com.subham.service;

import java.time.LocalDateTime;
import java.util.List;

import com.subham.model.Refund;
import com.subham.payload.dto.RefundDTO;

public interface RefundService {
	
	
	RefundDTO createrefund(RefundDTO refund) throws Exception;
	
	List<RefundDTO> getAllRefund() throws Exception;
	
	List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception;
	
	List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception;
	
	List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId,LocalDateTime startDate,LocalDateTime endDate) throws Exception;
	
	List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;
	
	RefundDTO getRefundById(Long refundId)throws Exception;
	
	void deleteRefund(long refundId) throws  Exception;
	
	
	
	

}
