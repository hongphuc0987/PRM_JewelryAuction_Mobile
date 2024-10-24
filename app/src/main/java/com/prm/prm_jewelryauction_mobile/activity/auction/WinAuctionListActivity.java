package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.WinAuctionAdapter.WinAuctionAdapter;
import com.prm.prm_jewelryauction_mobile.model.ApiResponseWinAuction;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.service.impl.ApiOrderServiceImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WinAuctionListActivity extends AppCompatActivity {

    private static final String TAG = "WinAuctionListActivity";
    private RecyclerView winAuctionRecyclerView;
    private WinAuctionAdapter winAuctionAdapter;
    private List<AuctionModel> auctionList;
    private ApiOrderServiceImpl apiOrderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_auction_list);

        ImageButton backButton = findViewById(R.id.backButton);

        winAuctionRecyclerView = findViewById(R.id.winAuctionRecyclerView);
        winAuctionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ApiOrderServiceImpl
        apiOrderService = new ApiOrderServiceImpl(this);

        // Load auction data
        loadWinAuctionData();

        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void loadWinAuctionData() {
        apiOrderService.getOrderList().enqueue(new Callback<ApiResponseWinAuction>() {
            @Override
            public void onFailure(Call<ApiResponseWinAuction> call, Throwable t) {
                // Display error message and log it
                Toast.makeText(WinAuctionListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API Call Failed", t);
            }

            @Override
            public void onResponse(Call<ApiResponseWinAuction> call, Response<ApiResponseWinAuction> response) {
                // Log the request URL
                Log.d(TAG, "API Call URL: " + call.request().url());

                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 200) {
                    // Get auction list from response
                    auctionList = response.body().getData();
                    Log.d(TAG, response.body().getData().get(0).toString());
                    if (auctionList == null || auctionList.isEmpty()) {
                        // No auction items available, show a message
                        findViewById(R.id.noAuctionTextView).setVisibility(View.VISIBLE);
                        winAuctionRecyclerView.setVisibility(View.GONE);
                    } else {
                        // Populate RecyclerView with auction items
                        winAuctionAdapter = new WinAuctionAdapter(auctionList, WinAuctionListActivity.this);
                        winAuctionRecyclerView.setAdapter(winAuctionAdapter);
                        findViewById(R.id.noAuctionTextView).setVisibility(View.GONE); // Hide "no auctions" message
                        winAuctionRecyclerView.setVisibility(View.VISIBLE); // Show RecyclerView

                        // Log the first auction item for debugging
                        Log.d(TAG, "First auction item: " + auctionList.get(0).toString());
                    }
                } else {
                    // Handle unsuccessful response
                    String errorMessage = response.message() != null ? response.message() : "Failed to load data";
                    Toast.makeText(WinAuctionListActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Response error: " + errorMessage);
                }
            }
        });
    }
}
