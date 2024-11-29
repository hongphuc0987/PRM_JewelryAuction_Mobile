package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.data.request.auction.BiddingRequest;
import com.prm.prm_jewelryauction_mobile.data.request.auction.CreateAuctionRequest;
import com.prm.prm_jewelryauction_mobile.data.response.BiddingHistoryResponse;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiAuctionService {
    @GET("/api/v1/auction")
    Call<List<AuctionModel>> getAuctionList();

    @GET("/api/v1/auction/{id}")
    Call<AuctionModel> getAuction(@Path("id") long id);

    @POST("/api/v1/bidding")
    Call<ResponseBody> bidding(@Body BiddingRequest request);
    @POST("/api/v1/auction")
    Call<Void> createAuction(@Body CreateAuctionRequest request);

    @GET("/api/v1/auction/bids/{auctionId}")
    Call<BiddingHistoryResponse> bidhistory(@Path("auctionId") long auctionId);
}
