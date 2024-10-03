package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Orders;
import com.examly.springapp.service.OrderService;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;

   @PostMapping("/api/order")
    public ResponseEntity<Orders> addOrder(@RequestBody Orders o ){
        Orders o1=service.addOrder(o);
        if(o1!=null)
            return new ResponseEntity<>(o1,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(o1,HttpStatus.INTERNAL_SERVER_ERROR);
    }
     
    @GetMapping("/api/order/user/{userId}")
    public ResponseEntity<List<Orders>> getOrderByUserId(@PathVariable int userId){
       List<Orders> o2=service.getOrdersByUserId(userId);
        if(o2!=null)
            return new ResponseEntity<>(o2,HttpStatus.OK);
        else
            return new ResponseEntity<>(o2,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/api/order")
    public ResponseEntity<List<Orders>> getAllOrder(){
        List<Orders> o2=service.getAllOrders();
        if(o2!=null){
            return new ResponseEntity<>(o2,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/order/customer/{customerId}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerId(@PathVariable long customerId){
       List<Orders> o2=service.getOrdersByCustomerId(customerId);
        if(o2!=null)
            return new ResponseEntity<>(o2,HttpStatus.OK);
        else
            return new ResponseEntity<>(o2,HttpStatus.NOT_FOUND);
    }


    }
