package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class CollectionModel extends BaseModel{
    private int id;
    private String name;
    private BrandModel brand;


    public CollectionModel(String createdAt, String updatedAt, int id, String name, BrandModel brand) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BrandModel getBrand() {
        return brand;
    }
}
