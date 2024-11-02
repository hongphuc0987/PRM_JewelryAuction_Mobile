package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.data.request.auth.UserSignInRequest;
import com.prm.prm_jewelryauction_mobile.data.request.auth.UserSignUpRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiAuthService {
    @POST("api/v1/user/signup")
    Call<Void> signupUser(@Body UserSignUpRequest userSignUpRequest);


    @POST("api/v1/user/signin")
    Call<ResponseBody> signinUser(@Body UserSignInRequest userSignInRequest);

    @POST("/api/v1/user/refresh")
    Call<ResponseBody> refreshToken(@Body String refreshToken);

}
