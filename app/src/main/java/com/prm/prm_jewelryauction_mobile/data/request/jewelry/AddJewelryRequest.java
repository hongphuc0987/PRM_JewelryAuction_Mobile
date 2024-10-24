package com.prm.prm_jewelryauction_mobile.data.request.jewelry;

import android.util.Log;

import com.prm.prm_jewelryauction_mobile.model.JewelryMaterialModel;

import java.util.List;

public class AddJewelryRequest {
    private String name;

    private String description;

    private Long category;

    private float weight;

    private String size;

    private String color;

    private String sex;

    private String brand;

    private String jewelryCondition;

    private String collection;

    private List<JewelryMaterialRequest> materials;

    public AddJewelryRequest(String name, String description, Long category, float weight, String size, String color, String sex, String brand, String jewelryCondition, String collection, List<JewelryMaterialRequest> materials) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.sex = sex;
        this.brand = brand;
        this.jewelryCondition = jewelryCondition;
        this.collection = collection;
        this.materials = materials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getJewelryCondition() {
        return jewelryCondition;
    }

    public void setJewelryCondition(String jewelryCondition) {
        this.jewelryCondition = jewelryCondition;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public List<JewelryMaterialRequest> getMaterials() {
        return materials;
    }

    public void setMaterials(List<JewelryMaterialRequest> materials) {
        this.materials = materials;
    }

}
