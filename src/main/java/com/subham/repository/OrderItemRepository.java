package com.subham.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subham.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
