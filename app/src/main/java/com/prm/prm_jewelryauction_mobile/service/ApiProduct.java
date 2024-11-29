package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.Valuation;
import com.prm.prm_jewelryauction_mobile.model.ValuationDetailResponse;
import com.prm.prm_jewelryauction_mobile.model.ValuationRequest;
import com.prm.prm_jewelryauction_mobile.model.ValuationResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiProduct {
    @GET("/api/v1/jewelry/me")
    Call<List<JewelryModel>> getProduct();

    @GET("/api/v1/jewelry/{id}")
    Call<JewelryModel> getProductById(@Path("id") int id);

    @POST("/api/v1/valuating")
    Call<ResponseBody> valuationProduct(@Body ValuationRequest valuationRequest);

    @GET("/api/v1/valuating/me")
    Call<ValuationResponse> getValuation();

    @GET("/api/v1/valuating/{id}")
    Call<ValuationDetailResponse> getValuationByID(@Path("id") int id);
}
