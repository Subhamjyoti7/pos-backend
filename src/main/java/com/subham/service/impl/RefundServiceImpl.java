package com.subham.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subham.mapper.RefundMapper;
import com.subham.model.Branch;
import com.subham.model.Order;
import com.subham.model.Refund;
import com.subham.model.User;
import com.subham.payload.dto.RefundDTO;
import com.subham.repository.OrderRepository;
import com.subham.repository.RefundRepository;
import com.subham.service.RefundService;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
	
	private final UserService userService;
	
	private final RefundRepository refundRepository;
	private final OrderRepository  orderRepository;

	@Override
	public RefundDTO createrefund(RefundDTO refund) throws Exception {
	User cashier=userService.getCurrentUser();
	Order order=orderRepository.findById(refund.getOrderId()).orElseThrow(
			()-> new Exception("Order not found"));
	
	Branch  branch=order.getBranch();
	
	Refund createrefund=Refund.builder()
			.order(order)
			.cashier(cashier)
			.branch(branch)
			.resson(refund.getResson())
			.amount(refund.getAmount())
			.createdAt(refund.getCreatedAt())
			.build();
	
	Refund savedRefund=refundRepository.save(createrefund);
		return RefundMapper.toDTO(savedRefund);
	}
	
	

	@Override
	public List<RefundDTO> getAllRefund() throws Exception {
		
		return refundRepository.findAll().stream().map(RefundMapper:: toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
	
		return refundRepository.findByCashierId(cashierId).stream()
				.map(RefundMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception {
		return refundRepository.findByShiftReportId(shiftReportId).stream()
				.map(RefundMapper::toDTO).collect(Collectors.toList());
	
	}

	@Override
	public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate,
			LocalDateTime endDate) throws Exception {
	
		return refundRepository.findByCashierIdAndCreatedAtBetween(null, startDate, endDate).stream()
				.map(RefundMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
		
		return refundRepository.findByBranchId(branchId).stream()
				.map(RefundMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public RefundDTO getRefundById(Long refundId) throws Exception {
		
		return refundRepository.findById(refundId)
				.map(RefundMapper :: toDTO).orElseThrow(
						()-> new Exception("refund not found"));
	}

	@Override
	public void deleteRefund(long refundId) throws Exception {
		this.getRefundById(refundId);
		
		refundRepository.deleteById(refundId);
		
		
		
	}

}
