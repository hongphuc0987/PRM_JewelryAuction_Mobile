package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.os.Bundle;
import android.util.Log; // Import Log class
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.WinAuctionAdapter.WinAuctionAdapter;
import com.prm.prm_jewelryauction_mobile.model.AuctionItem;
import com.prm.prm_jewelryauction_mobile.model.ApiResponseWinAuction; // Import the ApiResponse class
import com.prm.prm_jewelryauction_mobile.service.impl.ApiOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WinAuctionListActivity extends AppCompatActivity {

    private static final String TAG = "WinAuctionListActivity";  // Log tag for this activity
    private RecyclerView winAuctionRecyclerView;
    private WinAuctionAdapter winAuctionAdapter;
    private List<AuctionItem> auctionList;
    private ApiOrderServiceImpl apiOrderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_auction_list);
        ImageButton backButton = findViewById(R.id.backButton); // Get back button reference

        winAuctionRecyclerView = findViewById(R.id.winAuctionRecyclerView);
        winAuctionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load auction data
        apiOrderService = new ApiOrderServiceImpl(this);
        loadWinAuctionData();

        // Set back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Finish the current activity and go back to the previous one
            }
        });
    }

    private void loadWinAuctionData() {
        apiOrderService.getOrderList().enqueue(new Callback<ApiResponseWinAuction>() { // Use ApiResponse
            @Override
            public void onFailure(Call<ApiResponseWinAuction> call, Throwable t) {
                Toast.makeText(WinAuctionListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API Call Failed", t);  // Log the error
            }

            @Override
            public void onResponse(Call<ApiResponseWinAuction> call, Response<ApiResponseWinAuction> response) {
                // Log the API call URL
                Log.d(TAG, "API Call URL: " + call.request().url());
                Log.d(TAG, "onBindViewHolder: "+(response.body().getData().isEmpty()));

                if (response.isSuccessful() && response.body() != null) {
                    auctionList = response.body().getData(); // Get the data from the ApiResponse

                    // Check if auctionList is empty
                    if (auctionList != null && auctionList.isEmpty()) {
                        // Show the message and hide the RecyclerView
                        findViewById(R.id.noAuctionTextView).setVisibility(View.VISIBLE);
                        winAuctionRecyclerView.setVisibility(View.GONE);
                    } else {
                        // Update RecyclerView
                        winAuctionAdapter = new WinAuctionAdapter(auctionList, WinAuctionListActivity.this);
                        winAuctionRecyclerView.setAdapter(winAuctionAdapter);
                        findViewById(R.id.noAuctionTextView).setVisibility(View.GONE); // Hide the message
                        winAuctionRecyclerView.setVisibility(View.VISIBLE); // Show the RecyclerView
                    }
                } else if (response.raw().message().isEmpty()) {
                    Toast.makeText(WinAuctionListActivity.this, "Token is expired", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WinAuctionListActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
