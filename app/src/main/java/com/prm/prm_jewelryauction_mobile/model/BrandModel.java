package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class BrandModel extends  BaseModel{
    private int id;
    private String name;

    public BrandModel(String createdAt, String updatedAt, int id, String name) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
