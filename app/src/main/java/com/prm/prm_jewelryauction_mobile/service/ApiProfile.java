package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.model.ProfileModel;
import com.prm.prm_jewelryauction_mobile.model.ProfileResponse;
import com.prm.prm_jewelryauction_mobile.model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProfile {
    @GET("/api/v1/user/me")
    Call<ProfileResponse> getUser();
}
