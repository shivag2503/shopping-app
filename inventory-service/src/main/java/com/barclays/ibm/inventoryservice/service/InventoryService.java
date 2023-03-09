package com.barclays.ibm.inventoryservice.service;

import com.barclays.ibm.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
}
