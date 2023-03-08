package com.barclays.ibm.orderservice.repository;

import com.barclays.ibm.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
