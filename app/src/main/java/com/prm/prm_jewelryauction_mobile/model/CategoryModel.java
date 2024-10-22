package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class CategoryModel extends BaseModel {
    private int id;
    private String name;


    public CategoryModel(String createdAt, String updatedAt, int id, String name) {
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
}
