package com.webstore.web;

import com.webstore.service.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductsInfo {
    List<ProductDTO> products = new ArrayList<>();

    public ProductsInfo(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
