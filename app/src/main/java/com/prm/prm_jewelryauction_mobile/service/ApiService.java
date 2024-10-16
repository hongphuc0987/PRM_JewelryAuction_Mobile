package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.data.UserSignUpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/v1/user/signup")
    Call<Void> signupUser(@Body UserSignUpRequest userSignUpRequest);
}
