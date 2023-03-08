package com.barclays.ibm.productservice.service;

import com.barclays.ibm.productservice.dto.ProductRequest;
import com.barclays.ibm.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
