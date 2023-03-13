package com.barclays.ibm.orderservice.service;

import com.barclays.ibm.orderservice.dto.InventoryResponse;
import com.barclays.ibm.orderservice.dto.OrderLineItemsDto;
import com.barclays.ibm.orderservice.dto.OrderRequestDto;
import com.barclays.ibm.orderservice.model.Order;
import com.barclays.ibm.orderservice.model.OrderLineItems;
import com.barclays.ibm.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public String placeOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());



            // Call inventory service and place the order if the product is in stock
            InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/v1/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsIsInStock =
                    Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);

            if (Boolean.TRUE.equals(allProductsIsInStock)) {
                orderRepository.save(order);
                return "Order placed successfully!!";
            } else {
                throw new IllegalArgumentException("Product is not in stock. Try again later!!");
            }


    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}