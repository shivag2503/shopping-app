package com.barclays.ibm.productservice.repository;

import com.barclays.ibm.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
