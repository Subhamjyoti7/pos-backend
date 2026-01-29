package com.subham.service;

import java.util.List;

import com.subham.domain.OrderStatus;
import com.subham.domain.PaymentType;
import com.subham.payload.dto.OrderDTO;

public interface OrderService {
	
	OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
	
	OrderDTO getOrderById(Long id)throws Exception;
	List<OrderDTO> getOtrdersByBranch(Long branchId,Long customerId,
			Long cashierId,PaymentType paymentType,
			OrderStatus status) throws Exception;
	
	List<OrderDTO> getOrderByCashier(Long cashierId);
	
	void  deleteOrder(Long id) throws Exception;
	
	List<OrderDTO> getTodayOrderByBranch(Long branchId)throws Exception;
	
	List<OrderDTO> getOrderByCustomerId(Long customerId )throws Exception;
	 
	List<OrderDTO>  getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
	
	
	
	
	
	
	
	

}
