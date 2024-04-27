package com.webstore.service;

import com.webstore.domain.Product;
import com.webstore.repository.ProductRepository;
import com.webstore.service.adapter.ProductAdapter;
import com.webstore.service.adapter.ReviewAdapter;
import com.webstore.service.dto.ProductDTO;
import com.webstore.service.dto.ReviewDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductAdapter.toObj(productDTO);
        repository.save(product);
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        if (products != null && products.size() > 0) {
            for (Product p : products) {
                productDTOs.add(ProductAdapter.toDto(p));
            }
        }
        return productDTOs;
    }
    public List<ProductDTO> searchProductsContainName(String name) {
        List<Product> products = repository.findByNameContaining(name);
        List<ProductDTO> productDTOs = new ArrayList<>();
        if (products != null && products.size() > 0) {
            for (Product p : products) {
                productDTOs.add(ProductAdapter.toDto(p));
            }
        }
        return productDTOs;
    }
    public void updateProduct(ProductDTO productDTO) {
        Product product = ProductAdapter.toObj(productDTO);
        repository.save(product);
    }

    public void removeProduct(String productNumber) {
        repository.deleteById(productNumber);
    }

    public ProductDTO getProductDetail(String productNumber) {
        Product product = repository.findByProductNumber(productNumber);
        return ProductAdapter.toDto(product);
    }

    public ProductDTO makeReview(String productNumber, ReviewDTO reviewDTO) {
        Product product = repository.findByProductNumber(productNumber);
        if (reviewDTO != null) {
            if (reviewDTO.getId() == null || reviewDTO.getId().equals("")) {
                String generatedString = RandomStringUtils.randomAlphabetic(10);
                reviewDTO.setId(generatedString);
            }
            reviewDTO.setDate(new Date());
            if (product != null) {
                removeProduct(productNumber);
                product.getReviewList().add(ReviewAdapter.toObj(reviewDTO));
                Product p = repository.save(product);
                return ProductAdapter.toDto(p);
            } else {
                return null;
            }
        }
        return null;
    }
}
