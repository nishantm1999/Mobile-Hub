package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Mobile;

@Repository
public interface MobileRepo extends JpaRepository<Mobile,Long> {
    public Mobile findByModel(String mobile);
}
