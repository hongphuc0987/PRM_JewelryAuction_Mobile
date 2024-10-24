package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.model.ApiResponseWinAuction;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiOrderService {
    @GET("api/v1/auction/win")
    Call<ApiResponseWinAuction> getOrderList();
}
