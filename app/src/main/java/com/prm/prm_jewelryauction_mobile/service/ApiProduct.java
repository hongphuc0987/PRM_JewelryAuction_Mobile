package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.model.JewelryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiProduct {
    @GET("/api/v1/jewelry/me")
    Call<List<JewelryModel>> getProduct();

    @GET("/api/v1/jewelry/{id}")
    Call<JewelryModel> getProductById(@Path("id") int id);
}
