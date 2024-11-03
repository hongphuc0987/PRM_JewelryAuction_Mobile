package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.data.response.WishListRespone;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiWishListService {
    @GET("/api/v1/wishlist")
    Call<WishListRespone> getWishList();

    @POST("/api/v1/wishlist")
    Call<Void> addWishList(@Body Long auctionId);

    @DELETE("/api/v1/wishlist/{id}")
    Call<Void> deleteWishList(@Path("id") long id);
}
