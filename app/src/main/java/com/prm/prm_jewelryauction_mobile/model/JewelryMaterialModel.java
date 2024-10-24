package com.prm.prm_jewelryauction_mobile.model;

public class JewelryMaterialModel {
    private int id;
    private MaterialModel material;
    private double weight;

    public JewelryMaterialModel(int id, MaterialModel material, double weight) {
        this.id = id;
        this.material = material;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public MaterialModel getMaterial() {
        return material;
    }

    public double getWeight() {
        return weight;
    }
}
