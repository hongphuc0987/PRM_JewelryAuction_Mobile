package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class BaseModel {
    private String createdAt;
    private String updatedAt;

    public BaseModel(String createdAt, String updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
