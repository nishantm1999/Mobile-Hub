package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mobile{
    @Id
    @GeneratedValue
    private Long mobileId;
    private String model;
    private String brand;
    private String imageUrl;
    private String description;
    private double price;
    private int quantity;

    public Mobile(){}

    public Mobile(String model, String brand, String imageUrl, String description, double price, int quantity) {
        this.model = model;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public Long getMobileId() {
        return mobileId;
    }
    public void setMobileId(Long mobileId) {
        this.mobileId = mobileId;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 
}
