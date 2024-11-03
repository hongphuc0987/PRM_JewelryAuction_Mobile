package com.prm.prm_jewelryauction_mobile.activity.wishlist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.AuctionAdapter.WishListAdapter;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.data.response.WishListRespone;
import com.prm.prm_jewelryauction_mobile.service.ApiWishListService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class WishListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WishListAdapter adapter;
    private ProgressBar progressBar;

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.recyclerViewWishList);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> onBackPressed());


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchWishList();
    }

    private void fetchWishList() {
        ApiWishListService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiWishListService.class);
        Call<WishListRespone> call = apiService.getWishList();

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<WishListRespone>() {
            @Override
            public void onResponse(Call<WishListRespone> call, Response<WishListRespone> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new WishListAdapter(response.body().getData());
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("WishListActivity", "Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<WishListRespone> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("WishListActivity", "Error: " + t.getMessage());
            }
        });
    }

}

