package com.subham.payload.dto;

import java.time.LocalDateTime;

import com.subham.domain.PaymentType;
import com.subham.model.Branch;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class RefundDTO {
	
	
	private Long id;
	

	private OrderDTO order;
	
	private Long orderId;
	
	private String resson;
	
	private Double amount;
	
	//private  ShiftReport  shiftReport;
	
	private Long shiftReportId;
	

	private UserDto cashier;
	private String cashierName;
	

	private  Branch branch;
	
	private Long branchId;
	
	private PaymentType paymentType;
	
	private LocalDateTime createdAt;

}
