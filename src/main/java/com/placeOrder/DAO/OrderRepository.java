package com.placeOrder.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeOrder.entity.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Long>{

}
