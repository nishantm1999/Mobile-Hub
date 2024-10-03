package com.examly.springapp.service;
import java.util.List;

import com.examly.springapp.model.Orders;

public interface OrderService {
  public Orders addOrder(Orders order);
  public List<Orders> getAllOrders();
  public  Orders getOrderById(Long orderId);
  public  List<Orders> getOrdersByCustomerId(Long customerId);
  public  List<Orders>getOrdersByUserId(long userId);   
  
}

