package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.model.Mobile;
import com.examly.springapp.model.Orders;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.repository.MobileRepo;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.repository.UserRepo;
@Service
public class OrderServiceImpl  implements OrderService{
    @Autowired
    private OrderRepo oRepo;

    @Autowired
    private  UserRepo uRepo;

    @Autowired
    private  CustomerRepo cRepo;

    @Autowired
    private MobileRepo mobileRepo;
 

    // @Override
    // public Orders addOrder(Orders order) {
    //     Customer customer = cRepo.findById(order.getCustomer().getCustomerId()).get();
    //     order.setCustomer(customer);
    //     long mobileId= order.getMobiles()
    //     Mobile mobile = mobileRepo.findById(order.getMobiles()()).get();
    //     return oRepo.save(order);
    // }

    @Override
    public Orders addOrder(Orders order) {
        Customer customer = cRepo.findById(order.getCustomer().getCustomerId()).get();
        order.setCustomer(customer);
    
        List<Mobile> mobiles = new ArrayList<>();
        for(Mobile mobile : order.getMobiles()) {
            Mobile mobileFromDb = mobileRepo.findById(mobile.getMobileId()).get();
            mobiles.add(mobileFromDb);
        }
        order.setMobiles(mobiles);
    
        return oRepo.save(order);
    }
    
    

    @Override
    public List<Orders> getAllOrders() {

       return oRepo.findAll();
    }

    @Override
    public Orders getOrderById(Long orderId) {
       return null;
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Long customerId) {
        return oRepo.findByCustomerCustomerId(customerId);
    }

    @Override
    public List<Orders> getOrdersByUserId(long userId) {
      return null;
    
}
}
