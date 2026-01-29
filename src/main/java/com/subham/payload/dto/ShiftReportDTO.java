package com.subham.payload.dto;

import java.time.LocalDateTime;
import java.util.List;


import com.subham.model.PaymentSummary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShiftReportDTO {
	
private Long id;
	
	private LocalDateTime shiftStart;
	
	private LocalDateTime shiftEnd;
	
	private Double  totalSales;
	
	private Double totalrefunds;
	
	private Double netSale;
	
	private int totalOrders;
	

	private UserDto cashier;
	
	private Long cashierId;
	private long branchId;
	

	private BranchDTO branch;

	private List<PaymentSummary> paymentSummaries;

	private List<ProductDTO> topSellingProducts;
	
	

	private List<OrderDTO> recentOrders;
	
	
	private List<RefundDTO> refunds;
	

}
