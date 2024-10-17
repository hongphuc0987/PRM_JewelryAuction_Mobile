package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class MaterialModel extends BaseModel{
    private int id;
    private String name;
    private String unit;

    public MaterialModel(String createdAt, String updatedAt, int id, String name, String unit) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
}
