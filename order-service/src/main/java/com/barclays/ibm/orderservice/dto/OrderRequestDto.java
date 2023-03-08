package com.barclays.ibm.orderservice.dto;

import com.barclays.ibm.orderservice.model.OrderLineItems;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}