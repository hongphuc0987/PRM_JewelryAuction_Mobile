package com.prm.prm_jewelryauction_mobile.data.request.jewelry;

public class JewelryMaterialRequest {
    private Long idMaterial;

    private Float weight;

    public JewelryMaterialRequest(Long idMaterial, Float weight) {
        this.idMaterial = idMaterial;
        this.weight = weight;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
