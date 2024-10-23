package com.prm.prm_jewelryauction_mobile.service;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiWallet {
    @POST("/api/v1/deposit")
    Call<Void> depositWallet(@Query("amount") long amount);
}
