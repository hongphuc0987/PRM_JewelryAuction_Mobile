package com.prm.prm_jewelryauction_mobile.model;

public class JewelryImageModel {
    private int id;
    private String url;

    public JewelryImageModel(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
