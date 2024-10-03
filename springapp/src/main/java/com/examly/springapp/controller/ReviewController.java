package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Review;
import com.examly.springapp.service.ReviewService;

@RestController
public class ReviewController {
    
    @Autowired
    private ReviewService service;

    @PostMapping("/api/review")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        Review r = service.addReview(review);
        if(r!=null){
            return new ResponseEntity<>(r,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/review/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable int reviewId){
        Review r = service.deleteReview(reviewId);
        if(r!=null){
            return new ResponseEntity<>(r,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/review")
    public ResponseEntity<List<Review>> getReviews(){
        List<Review> reviews = service.getAllReview();
        if(reviews.size()>0){
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
