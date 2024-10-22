package com.prm.prm_jewelryauction_mobile.activity;

public class Product {
    private String name;
    private int imageResourceId; // Resource ID cho hình ảnh sản phẩm

    public Product(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

