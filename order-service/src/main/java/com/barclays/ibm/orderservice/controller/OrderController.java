package com.barclays.ibm.orderservice.controller;

import com.barclays.ibm.orderservice.dto.OrderLineItemsDto;
import com.barclays.ibm.orderservice.dto.OrderRequestDto;
import com.barclays.ibm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody  OrderRequestDto orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order placed successfully!!!";
    }
}
