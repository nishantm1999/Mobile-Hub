    package com.examly.springapp.model;

    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.JoinTable;
    import jakarta.persistence.ManyToMany;
    import jakarta.persistence.ManyToOne;

    @Entity
    public class Orders {
    @Id
    @GeneratedValue
    private long oderId;
    private double orderPrice;
    private int quantity;
    
    @ManyToMany
    @JoinTable(
        name="order_mobiles",
        joinColumns = @JoinColumn(name="order_id"),
        inverseJoinColumns = @JoinColumn(name="mobile_id")
    )
    List<Mobile>mobiles=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public long getOderId() {
        return oderId;
    }
    public void setOderId(long oderId) {
        this.oderId = oderId;
    }
    public double getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public List<Mobile> getMobiles() {
        return mobiles;
    }
    public void setMobiles(List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    } 
