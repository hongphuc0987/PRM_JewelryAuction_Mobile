package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.AuctionAdapter.BiddingHistoryAdapter;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.data.response.BiddingHistory;
import com.prm.prm_jewelryauction_mobile.data.response.BiddingHistoryResponse;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BiddingHistoryAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvNoData;

    private Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_history);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvNoData = findViewById(R.id.tvNoData);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> onBackPressed());


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        long auctionId = getIntent().getLongExtra("AUCTION_ID", -1);
        if (auctionId != -1) {
            fetchBiddingHistory(auctionId);
        } else {
            Log.e("BiddingHistoryActivity", "Invalid Auction ID");
        }
    }

    private void fetchBiddingHistory(long auctionId) {
        ApiAuctionService apiService = RetrofitClient.getRetrofitInstance().create(ApiAuctionService.class);
        Call<BiddingHistoryResponse> call = apiService.bidhistory(auctionId);

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<BiddingHistoryResponse>() {
            @Override
            public void onResponse(Call<BiddingHistoryResponse> call, Response<BiddingHistoryResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("BiddingHistoryActivity", "Response body: " + response.body().toString());
                    List<BiddingHistory> historyList = response.body().getHistoryList();
                    if (historyList != null && !historyList.isEmpty()) {
                        adapter = new BiddingHistoryAdapter(historyList);
                        recyclerView.setAdapter(adapter);
                        Log.d("BiddingHistoryActivity", "History list size: " + historyList.size());
                    } else {
                        tvNoData.setVisibility(View.VISIBLE);
                        Log.d("BiddingHistoryActivity", "No bidding history data found.");
                    }
                } else {
                    Log.e("BiddingHistoryActivity", "Failed to fetch data: " + response.message());
                    tvNoData.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<BiddingHistoryResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("BiddingHistoryActivity", "Error: " + t.getMessage());
            }
        });
    }
}