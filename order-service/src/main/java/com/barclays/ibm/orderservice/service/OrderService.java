package com.barclays.ibm.orderservice.service;

import com.barclays.ibm.orderservice.dto.OrderRequestDto;

public interface OrderService {

    void placeOrder(OrderRequestDto orderRequest);
}
