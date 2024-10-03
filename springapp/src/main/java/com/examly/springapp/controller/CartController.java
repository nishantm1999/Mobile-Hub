package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.examly.springapp.model.Cart;
import com.examly.springapp.service.CartServiceImpl;
import java.util.*;
@RestController
public class CartController {
    
    @Autowired CartServiceImpl service;
    
    @PostMapping("/api/cart")
    private ResponseEntity<Cart> addCart(@RequestBody Cart cart){
        System.out.println(cart);
          Cart ct=service.addCart(cart);
          if(ct!=null)
          return new ResponseEntity<>(ct,HttpStatus.CREATED);
          else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping("/api/cart/{cartId}")
     private ResponseEntity<Cart> editCart(@PathVariable long cartId,@RequestBody Cart cart){
        System.out.println(cart);
        Cart ct=service.editCart(cartId, cart);
        if(ct!=null){
        return new ResponseEntity<>(ct,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @GetMapping("/api/cart/userId/{userId}")
    // private ResponseEntity<Cart> getCartByUserId(@PathVariable long userId){
    // }

    @DeleteMapping("/api/cart/{cartId}/mobile/{mobileId}")
    private ResponseEntity<Cart> removeMobileFromCart(@PathVariable long cartId, @PathVariable long mobileId){
        Cart c= service.removeMobileFromCart(cartId,mobileId);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/cart/{cartId}")
    private ResponseEntity<Cart> removeAllMobiles(@PathVariable long cartId){
        Cart c=service.removeAllMobiles(cartId);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/cart/customer/{customerId}")
    private ResponseEntity<Cart> getCartByCustomerId(@PathVariable long customerId){
        Cart c= service.getCartByCustomerId(customerId);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/cart")
    private ResponseEntity<List<Cart>> getCart(){
        List<Cart> c= service.getCart();
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
