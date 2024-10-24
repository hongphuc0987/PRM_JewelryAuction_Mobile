package com.prm.prm_jewelryauction_mobile.service;

import com.prm.prm_jewelryauction_mobile.data.request.jewelry.AddJewelryRequest;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.model.BrandModel;
import com.prm.prm_jewelryauction_mobile.model.CategoryModel;
import com.prm.prm_jewelryauction_mobile.model.CollectionModel;
import com.prm.prm_jewelryauction_mobile.model.MaterialModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface ApiJewelryService {
    @GET("/api/v1/category")
    Call<List<CategoryModel>> getListCategory();
    @GET("/api/v1/category/{id}")
    Call<CategoryModel> getCategory(@Path("id") long id);

    @GET("/api/v1/brand")
    Call<List<BrandModel>> getListBrand();

    @GET("/api/v1/material")
    Call<List<MaterialModel>> getListMaterial();

    @GET("/api/v1/material{id}")
    Call<MaterialModel> getMaterial(@Path("id") long id);
    @GET("/api/v1/brand/{id}")
    Call<BrandModel> getBrand(@Path("id") long id);

    @GET("/api/v1/collection/brand/{id}")
    Call<List<CollectionModel>> getListCollectionByBrand(@Path("id") long id);

    @GET("/api/v1/collection/{id}")
    Call<CollectionModel> getCollection(@Path("id") long id);

    @Multipart
    @POST("/api/v1/jewelry")
    Call<ResponseBody> addJewelry(
            @PartMap Map<String, RequestBody> jewelryRequest,
            @Part MultipartBody.Part imageThumbnail,
            @Part List<MultipartBody.Part> imagesFile
    );
}
