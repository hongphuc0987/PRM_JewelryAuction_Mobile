package com.prm.prm_jewelryauction_mobile.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.prm.prm_jewelryauction_mobile.service.ApiAuthService;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://35.194.232.209:9090";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getRetrofitInstanceWithToken(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("accessToken", null);

                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("Content-Type", "application/json");

                        if (accessToken != null) {
                            builder.header("Authorization", "Bearer " + accessToken);
                        }

                        Request modifiedRequest = builder.build();
                        okhttp3.Response response = chain.proceed(modifiedRequest);

                        if (response.code() == 401) {
                            response.close();
                            Log.d("TOKEN_EXPIRED", "Token expired. Refreshing...");

                            boolean tokenRefreshed = refreshAccessTokenSync(context);

                            if (tokenRefreshed) {
                                accessToken = sharedPreferences.getString("accessToken", null);
                                if (accessToken != null) {
                                    // Retry the request with the new token
                                    builder.header("Authorization", "Bearer " + accessToken);
                                    modifiedRequest = builder.build();
                                    return chain.proceed(modifiedRequest);
                                }
                            }
                        }

                        return response;
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static boolean refreshAccessTokenSync(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String refreshToken = sharedPreferences.getString("refreshToken", null);

        if (refreshToken != null) {
            ApiAuthService apiService = getRetrofitInstance().create(ApiAuthService.class);
            try {
                Response<ResponseBody> response = apiService.refreshToken(refreshToken).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseBodyString);
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String newAccessToken = data.getString("accessToken");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("accessToken", newAccessToken);

                    if (data.has("refreshToken")) {
                        String newRefreshToken = data.getString("refreshToken");
                        editor.putString("refreshToken", newRefreshToken);
                    }

                    editor.apply();
                    return true;
                } else {
                    Log.e("API_ERROR", "Failed to refresh token: " + response.message());
                }
            } catch (Exception e) {
                Log.e("API_ERROR", "Error refreshing token: " + e.getMessage());
            }
        } else {
            Log.e("API_ERROR", "Refresh token not found.");
        }
        return false;
    }



}
