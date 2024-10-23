package com.prm.prm_jewelryauction_mobile.service.impl;

import android.content.Context;

import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.model.ApiResponseWinAuction;
import com.prm.prm_jewelryauction_mobile.service.ApiOrderService;

import retrofit2.Call;

public class ApiOrderServiceImpl implements ApiOrderService {

    private final ApiOrderService apiOrderService;

    public ApiOrderServiceImpl(Context context) {
        apiOrderService = RetrofitClient.getRetrofitInstanceWithToken(context).create(ApiOrderService.class);
    }

    @Override
    public Call<ApiResponseWinAuction> getOrderList() {
        return apiOrderService.getOrderList();
    }
}
