package com.examly.springapp.model;
 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
 
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private long cartId;
 
    @ManyToMany
    @JoinTable(
        name="cart_mobiles",
        joinColumns = @JoinColumn(name="cart_id"),
        inverseJoinColumns = @JoinColumn(name="mobile_id")
        )
    private List<Mobile> mobiles=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    
    private double totalAmount;
 
    public long getCartId() {
        return cartId;
    }
 
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
 
    public List<Mobile> getMobiles() {
        return mobiles;
    }
 
    public void setMobiles(List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

   


    public double getTotalAmount() {
        return totalAmount;
    }
 
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
 
    public void removeMobile(Mobile mobile){
        int index=-1;
        for (Mobile mb : mobiles) {
            if(mb.getMobileId()==(mobile.getMobileId())){
               index=mobiles.indexOf(mb); 
            } 
        }
        mobiles.remove(index);
    }
   
    public void removeAllMobile(){
        mobiles.clear();
    }

 
    public Customer getCustomer() {
        return customer;
    }
 
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void addMobile(Mobile mobile){
        mobiles.add(mobile);
    }
   
 
}
