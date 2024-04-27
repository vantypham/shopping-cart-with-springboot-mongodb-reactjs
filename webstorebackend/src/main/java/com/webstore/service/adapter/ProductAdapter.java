package com.webstore.service.adapter;

import com.webstore.domain.Product;
import com.webstore.domain.Review;
import com.webstore.service.dto.ProductDTO;
import com.webstore.service.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter {

    public static Product toObj(ProductDTO dto) {
        Product p = null;
        if (dto != null) {
            p = new Product(dto.getProductNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getNumberInStock());
        }
        //
        List<Review> reviewList = new ArrayList<>();
        List<ReviewDTO> reviewDTOs = dto.getReviewList();
        if (!reviewDTOs.isEmpty()) {
            for (ReviewDTO review : reviewDTOs) {
                reviewList.add(ReviewAdapter.toObj(review));
            }
        }
        p.setReviewList(reviewList);
        return p;
    }
    public static ProductDTO toDto(Product obj) {
        ProductDTO dto = null;
        if (obj != null) {
            dto = new ProductDTO(obj.getProductNumber(), obj.getName(), obj.getPrice(), obj.getDescription(), obj.getNumberInStock());
            List<Review> reviewList = obj.getReviewList();
            List<ReviewDTO> reviewDTOs = new ArrayList<>();
            if (!reviewList.isEmpty()) {
                for (Review review : reviewList) {
                    reviewDTOs.add(ReviewAdapter.toDto(review));
                }
            }
            dto.setReviewList(reviewDTOs);
        }
        return dto;
    }
}
