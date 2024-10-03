package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Review;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.repository.ReviewRepo;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepo rRepo;   
    
    @Autowired
    private CustomerRepo crepo;

    @Override
    public Review addReview(Review review) {
        return rRepo.save(review);
    }

    @Override
    public Review getReviewById(int reviewId) {
        if(rRepo.existsById(reviewId)){
            Review r = rRepo.findById(reviewId).get();
            rRepo.save(r);
            return r;
        }else{
            return null;
        }
    }

    @Override
    public List<Review> getAllReview() {
        return rRepo.findAll();
    }

    @Override
    public List<Review> getReviewByUserId(Long userId) {
        // if(crepo.existsById(userId)){
            return null;   
        // }
    }

    @Override
    public Review deleteReview(int reviewId) {
        if(rRepo.existsById(reviewId)){
            Review r = rRepo.findById(reviewId).get();
            rRepo.deleteById(reviewId);
            return r;
        }else{
            return null;
        }
    }
    
}
