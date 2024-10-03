package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Review;

@Service
public interface ReviewService{
    Review addReview(Review review);
    Review getReviewById(int reviewId);
    List<Review> getAllReview();
    List<Review> getReviewByUserId(Long userId);
    Review deleteReview(int reviewId);
}
