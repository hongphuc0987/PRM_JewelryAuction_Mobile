package com.prm.prm_jewelryauction_mobile.model;

public class Product {
    private String name;
    private double price;
    private String status;
    private int imageResourceId; // Resource ID cho hình ảnh sản phẩm

    public Product(String name, double price, String status, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}

