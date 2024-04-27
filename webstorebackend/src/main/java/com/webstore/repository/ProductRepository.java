package com.webstore.repository;

import com.webstore.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductNumber(String productNumber);
    List<Product> findByNameContaining(String productName);// return a list of products contains name
}
