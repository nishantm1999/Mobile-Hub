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

import com.examly.springapp.model.Customer;
import com.examly.springapp.model.User;
import com.examly.springapp.service.CustomerService;


@RestController
public class CustomerController {
    
    @Autowired
    CustomerService service;

    @GetMapping("/api/customer")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestBody Customer customer){
        List<Customer> c= service.getAllCustomers();
        if(c.size()>0)
         return new ResponseEntity<>(c,HttpStatus.OK);
         else
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/api/customer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        Customer c= service.registerCustomer(customer);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.CREATED);
        }
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/customer/user/{userId}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable long userId){
        Customer c= service.getCustomerByUserId(userId);
        if(c!=null)
        return new ResponseEntity<>(c,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long customerId){
        Customer c= service.getCustomerById(customerId);
        if(c!=null)
        return new ResponseEntity<>(c,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
