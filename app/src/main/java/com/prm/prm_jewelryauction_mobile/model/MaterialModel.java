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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name;
    }
}
