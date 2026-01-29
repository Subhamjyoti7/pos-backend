package com.subham.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.subham.model.Order;
import com.subham.model.Product;
import com.subham.model.Refund;
import com.subham.model.ShiftReport;
import com.subham.payload.dto.OrderDTO;
import com.subham.payload.dto.ProductDTO;
import com.subham.payload.dto.RefundDTO;
import com.subham.payload.dto.ShiftReportDTO;

public class ShiftReportMapper {
	
	public static ShiftReportDTO  toDTO(ShiftReport entity) {
		return ShiftReportDTO.builder()
				.id(entity.getId())
				.shiftStart(entity.getShiftStart())
				.shiftEnd(entity.getShiftEnd())
				.totalSales(entity.getTotalSales())
				.totalOrders(entity.getTotalOrders())
				.totalrefunds(entity.getTotalrefunds())
				.netSale(entity.getNetSale())
				.cashier(UserMapper.toDTO(entity.getCashier()))
				.cashierId(entity.getCashier().getId())
				.branchId(entity.getBranch().getId())
				.recentOrders(mapOrders(entity.getRecentOrders()))
				.topSellingProducts(mapProducts(entity.getTopSellingProducts()))
				.refunds(mapRefunds(entity.getRefunds()))
				.paymentSummaries(entity.getPaymentSummares())
				.build();
				
	}

	private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
	
		if(refunds == null || refunds.isEmpty()) { 
			return null; 
		}
		return refunds.stream().map(RefundMapper :: toDTO).collect(Collectors.toList());

	}

	private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
	    if (topSellingProducts == null || topSellingProducts.isEmpty()) {
	        return List.of();
	    }

	    return topSellingProducts.stream()
	            .map(ProductMapper::toDto) // ✅ CORRECT
	            .collect(Collectors.toList());
	}


	private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
		if(recentOrders== null || recentOrders.isEmpty()) {
			return null;
		}
		return recentOrders.stream().map(OrderMapper :: toDTO).collect(Collectors.toList());
	}

	

}
