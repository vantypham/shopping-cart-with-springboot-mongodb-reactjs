package com.webstore.service.adapter;

import com.webstore.domain.Review;
import com.webstore.service.dto.ReviewDTO;

public class ReviewAdapter {
    public static ReviewDTO toDto(Review review) {
        if (review != null) {
            return new ReviewDTO(review.getId(), review.getUsername(), review.getMessage(), review.getRate(), review.getDate());
        } else {
            return null;
        }
    }
    public static Review toObj(ReviewDTO review) {
        if (review != null) {
            return new Review(review.getId(), review.getUsername(), review.getMessage(), review.getRate(), review.getDate());
        } else {
            return null;
        }
    }
}
