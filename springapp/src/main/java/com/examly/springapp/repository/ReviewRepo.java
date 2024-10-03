package com.examly.springapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer>{
   
}
