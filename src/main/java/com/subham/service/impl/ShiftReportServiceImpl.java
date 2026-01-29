package com.subham.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.subham.controller.AuthController;
import com.subham.domain.PaymentType;
import com.subham.mapper.ShiftReportMapper;
import com.subham.model.Branch;
import com.subham.model.Order;
import com.subham.model.OrderItem;
import com.subham.model.PaymentSummary;
import com.subham.model.Product;
import com.subham.model.Refund;
import com.subham.model.ShiftReport;
import com.subham.model.User;
import com.subham.payload.dto.ShiftReportDTO;
import com.subham.repository.BranchRepository;
import com.subham.repository.OrderRepository;
import com.subham.repository.RefundRepository;
import com.subham.repository.ShiftReportrepository;
import com.subham.repository.UserRepository;
import com.subham.service.ShiftReportService;
import com.subham.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final AuthController authController;
	
	private final ShiftReportrepository   shiftReportrepository;
	
	private final UserService userService;
	
	private final BranchRepository branchRepository;
	
	private final RefundRepository refundRepository;
	
	private final OrderRepository orderRepository;
	
	private final UserRepository userRepository;


    

	@Override
	public ShiftReportDTO startShift() throws Exception {
		User currentUser=userService.getCurrentUser();
		LocalDateTime shiftStart=LocalDateTime.now();
		LocalDateTime startOfDay=shiftStart.withHour(0).withMinute(0).withSecond(0);
		
		LocalDateTime endOfDay=shiftStart.withHour(23).withMinute(59).withSecond(59);
		
		Optional<ShiftReport> existing=shiftReportrepository.findByCashierAndShiftStartBetween(currentUser, startOfDay, endOfDay);
		
		if(existing.isPresent()) {
			throw new Exception("shift already started today");
		}
		
		Branch branch=currentUser.getBranch();
		
		ShiftReport shiftReport=ShiftReport.builder()
				.cashier(currentUser)
				.shiftStart(shiftStart)
				.branch(branch)
				.build();
		
		ShiftReport savedreport=shiftReportrepository.save(shiftReport);
				
		
		return ShiftReportMapper.toDTO(savedreport);
	}

	@Override
	public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
		User currentuser=userService.getCurrentUser();
		
		ShiftReport shiftReport=shiftReportrepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentuser)
				.orElseThrow(()-> new Exception("Shift not found"));
		
		shiftReport.setShiftEnd(shiftEnd);
		List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(
				currentuser.getId(),
				shiftReport.getShiftStart(),
				shiftReport.getShiftEnd());
		
		double totalRefunds=refunds.stream()
				.mapToDouble(refund -> refund.getAmount()!= null?
						refund.getAmount():0.0).sum();
		List<Order> orders=orderRepository.findByCashierAndCreatedAtBetween(currentuser, shiftReport.getShiftStart(), shiftReport.getShiftEnd());
		double totalSales=orders.stream().mapToDouble(Order :: getTotlAmount).sum();
		
		int totalOrder=orders.size();
		
		double netSales=totalSales-totalRefunds;
		
		shiftReport.setTotalrefunds(totalRefunds);
		shiftReport.setTotalSales(totalSales);
		
		shiftReport.setTotalOrders(totalOrder);
		
		shiftReport.setNetSale(netSales);
		shiftReport.setRecentOrders(getRecentOrders(orders));
		shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
		shiftReport.setPaymentSummares(getPaymentSummeries(orders,totalSales));
		
		shiftReport.setRefunds(refunds);
		
		ShiftReport  savereport=shiftReportrepository.save(shiftReport);
		
		
		
		return ShiftReportMapper.toDTO(savereport);
	}

	
	@Override
	public ShiftReportDTO getShiftreportById(Long id) throws Exception {
		
		return shiftReportrepository.findById(id)
				.map(ShiftReportMapper :: toDTO)
				.orElseThrow(
				()-> new Exception("shift report not found with given id"+id));
	}

	@Override
	public List<ShiftReportDTO> getAllShiftReports() {
		
		List<ShiftReport> reports=shiftReportrepository.findAll();
		
		return reports.stream().map(ShiftReportMapper :: toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ShiftReportDTO> getShiftReportByBranchId(Long branchId) {
		
    List<ShiftReport> reports=shiftReportrepository.findByBranchId(branchId);
		
		return reports.stream().map(ShiftReportMapper :: toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId) {
    List<ShiftReport> reports=shiftReportrepository.findByCashierId(cashierId);
		
		return reports.stream().map(ShiftReportMapper :: toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception {
		User user=userService.getCurrentUser();
		ShiftReport shiftReport=shiftReportrepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user)
				.orElseThrow(()-> new Exception("no active shift found for date"));
		
		LocalDateTime  now=LocalDateTime.now();
		
		List<Order> orders=orderRepository.findByCashierAndCreatedAtBetween(user, shiftReport.getShiftStart(), now);
		
		List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(
				user.getId(),
				shiftReport.getShiftStart(),
				now);
		
		double totalRefunds=refunds.stream()
				.mapToDouble(refund -> refund.getAmount()!= null?
						refund.getAmount():0.0).sum();
	
		double totalSales=orders.stream().mapToDouble(Order :: getTotlAmount).sum();
		
		int totalOrder=orders.size();
		
		double netSales=totalSales-totalRefunds;
		
		shiftReport.setTotalrefunds(totalRefunds);
		shiftReport.setTotalSales(totalSales);
		
		shiftReport.setTotalOrders(totalOrder);
		
		shiftReport.setNetSale(netSales);
		shiftReport.setRecentOrders(getRecentOrders(orders));
		shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
		shiftReport.setPaymentSummares(getPaymentSummeries(orders,totalSales));
		
		shiftReport.setRefunds(refunds);
		
		ShiftReport  savereport=shiftReportrepository.save(shiftReport);
		
		
		
		
		
		return ShiftReportMapper.toDTO(savereport);
	}

	@Override
	public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
		
		User cashier=userRepository.findById(cashierId).orElseThrow(
				()-> new Exception("cashier not found with given id"+cashierId));
		
		LocalDateTime start=date.withHour(0).withMinute(0).withSecond(0);
		LocalDateTime end=date.withHour(23).withMinute(59).withSecond(59);
		ShiftReport report=shiftReportrepository.findByCashierAndShiftStartBetween(cashier, start, end)
				.orElseThrow(()-> new Exception("shift report not found for cashier"));
		
		
				
		return ShiftReportMapper.toDTO(report);
	}
	
	
	
	// --------------------------- Helper Methods------------------------------------
	
	private List<PaymentSummary> getPaymentSummeries(List<Order> orders, double totalSales) {
		
		Map<PaymentType, List<Order>> grouped=orders.stream()
				.collect(Collectors.groupingBy(order -> order.getPaymentType()!=null?
						order.getPaymentType():PaymentType.CASH));
		
		List<PaymentSummary> summeries= new ArrayList<>();
		
		for(Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()) {
			double amount= entry.getValue().stream()
					.mapToDouble(Order :: getTotlAmount).sum();
			
			int transactions=entry.getValue().size();
			
			double percent=(amount/totalSales)*100;
			
			
			PaymentSummary ps= new PaymentSummary();
			
			ps.setType(entry.getKey());
			ps.setTotalAmount(amount);
			ps.setTransactionCount(transactions);
			ps.setPercentage(percent);
			summeries.add(ps);
		}
		return summeries;
	}

	private List<Product> getTopSellingProducts(List<Order> orders) {
		
		
		Map<Product, Integer> productSalesMap= new HashMap<>();
		
		for(Order order:orders) {
			for(OrderItem  item:order.getItems()) {
				Product  product=item.getProduct();
				productSalesMap.put(product, productSalesMap.getOrDefault(product,0)+item.getQuantity());
			}
		}
		return productSalesMap.entrySet().stream()
				.sorted((a,b)->b.getValue().compareTo(a.getValue()) )
				.limit(5)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	private List<Order> getRecentOrders(List<Order> orders) {
		
		return orders.stream()
				.sorted(Comparator.comparing(Order::getCreatedAt).reversed())
				.limit(5)
				.collect(Collectors.toList());
	}


}
