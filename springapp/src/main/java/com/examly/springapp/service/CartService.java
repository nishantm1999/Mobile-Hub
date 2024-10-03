package com.examly.springapp.service;

import com.examly.springapp.model.Cart;
import java.util.*;
public interface CartService {
    Cart addCart(Cart cart);
    Cart editCart(Long cartId,Cart updatedCart);
    Cart removeMobileFromCart(Long cartId,Long mobileId);
    Cart removeAllMobiles(Long cartId);   
    Cart getCartByCustomerId(Long customerId);
    List<Cart> getCart();
}
