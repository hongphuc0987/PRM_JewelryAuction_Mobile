package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiAuctionService {
    @GET("/api/v1/auction")
    Call<List<AuctionModel>> getAuctionList();

    @GET("/api/v1/auction/{id}") // Sử dụng @Path để chỉ định id
    Call<AuctionModel> getAuction(@Path("id") long id);

}
