package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Cart;
import com.examly.springapp.model.Customer;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.repository.CartRepo;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.repository.MobileRepo;
import java.util.*;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private MobileRepo mobilerepo;

    @Autowired
    private CustomerRepo customerRepo;


      @Override
      public Cart addCart(Cart cart) {
        Customer customer = customerRepo.findById(cart.getCustomer().getCustomerId()).get();
        cart.setCustomer(customer);
        return cartRepo.save(cart); 

      } 
      @Override
      public Cart editCart(Long cartId, Cart updatedCart) {
        if(cartRepo.existsById(cartId)){
            updatedCart.setCartId(cartId);
             return cartRepo.save(updatedCart);
        }
        else return null;
      } 


      @Override
      public Cart removeAllMobiles(Long cartId) {
        if(cartRepo.existsById(cartId)){
            Cart cart=cartRepo.findById(cartId).get();
            // cart.removeAllMobile();
            cartRepo.deleteById(cartId);
            return cart;
           }
           else return null;
      }

      @Override
      public Cart removeMobileFromCart(Long cartId, Long mobileId) {
        if(cartRepo.existsById(cartId) && mobilerepo.existsById(mobileId)){
            Cart cart=cartRepo.findById(cartId).get();
            Mobile mob=mobilerepo.findById(mobileId).get();
            cart.removeMobile(mob);
            cartRepo.save(cart);
            return cart;
           }
        else return null;
      }

      @Override
      public Cart getCartByCustomerId(Long customerId) {
        Customer c = customerRepo.findById(customerId).orElse(null);
        if (c == null) {
          return null;
        }
       return cartRepo.findByCustomer(c);
      }


    
//     Customer customer = customerOptional.get();
//     List<Cart> carts = cartRepo.findAllByCustomer(customer);
    
//     if (carts.isEmpty()) {
//         return null; // No cart found for the customer
//     }
    
//     // Choose one cart based on specific criteria or return the first cart
//     return carts.get(0);
// }

            

      @Override
      public List<Cart> getCart() {
        return cartRepo.findAll();
      }
      
    
}
