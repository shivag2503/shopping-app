package com.barclays.ibm.orderservice.service;

import com.barclays.ibm.orderservice.dto.OrderRequestDto;

public interface OrderService {

    String placeOrder(OrderRequestDto orderRequest);
}
