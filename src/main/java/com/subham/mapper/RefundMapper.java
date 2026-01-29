package com.subham.mapper;


import com.subham.model.Refund;
import com.subham.payload.dto.RefundDTO;

public class RefundMapper {
	
	public static RefundDTO toDTO(Refund refund) {
		
		return RefundDTO.builder()
		.id(refund.getId())
		.orderId(refund.getOrder().getId())
		.resson(refund.getResson())
		.amount(refund.getAmount())
		.cashierName(refund.getCashier().getFullname())
		.branchId(refund.getBranch().getId())
		.shiftReportId(refund.getShiftReport().getId())
		.createdAt(refund.getCreatedAt())
		.build();
		
	}

}
