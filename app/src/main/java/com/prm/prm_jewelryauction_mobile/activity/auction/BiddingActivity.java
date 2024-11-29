package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.data.request.auction.BiddingRequest;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddingActivity extends AppCompatActivity {

    private TextView tvCurrentPrice, tvStepAuction, tvBidAmount;
    private Button btnAdd, btnSubtract, btnBid;

    private double step = 5000.0;
    private double currentPrice;
    private float bidAmount;
    private long auctionId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auction_bid_activity);

        // Ánh xạ các view
        tvCurrentPrice = findViewById(R.id.tvCurrentPrice);
        tvStepAuction = findViewById(R.id.tvStepAuction);
        tvBidAmount = findViewById(R.id.tvBidAmount);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnBid = findViewById(R.id.btnBid);

        auctionId = getIntent().getLongExtra("AUCTION_ID", -1);
        if (auctionId != -1) {
            loadAuctionDetails(auctionId);
        } else {
            Toast.makeText(this, "Invalid Auction ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnAdd.setOnClickListener(v -> {
            bidAmount += step;
            updateBidAmount();
        });

        btnSubtract.setOnClickListener(v -> {
            if (bidAmount >= currentPrice) {
                bidAmount -= step;
                updateBidAmount();
            } else {
                Toast.makeText(this, "Bid amount must be greater than or equal to the current price", Toast.LENGTH_SHORT).show();
            }
        });

        btnBid.setOnClickListener(v -> placeBid());
    }

    private void loadAuctionDetails(long auctionId) {
        ApiAuctionService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiAuctionService.class);
        Call<AuctionModel> call = apiService.getAuction(auctionId);

        call.enqueue(new Callback<AuctionModel>() {
            @Override
            public void onResponse(Call<AuctionModel> call, Response<AuctionModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuctionModel auction = response.body();
                    currentPrice = Double.parseDouble(auction.getCurrentPrice());
                    bidAmount = (float) (currentPrice + step);

                    tvCurrentPrice.setText("Current Price: " + currentPrice + " VND");
                    tvStepAuction.setText("Step: " + step + " VND");
                    updateBidAmount();
                } else {
                    Toast.makeText(BiddingActivity.this, "Failed to load auction details", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AuctionModel> call, Throwable t) {
                Toast.makeText(BiddingActivity.this, "Error loading auction", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                finish();
            }
        });
    }

    private void updateBidAmount() {
        tvBidAmount.setText("Bidding price: " + bidAmount + " VND");
    }

    private void placeBid() {
        BiddingRequest request = new BiddingRequest(auctionId, bidAmount);

        ApiAuctionService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiAuctionService.class);
        Call<ResponseBody> call = apiService.bidding(request);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(BiddingActivity.this, "Bid placed successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        // Lấy nội dung lỗi từ body response và hiển thị
                        String errorBody = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorBody);
                        String message = jsonObject.optString("message", "Unknown error");

                        Toast.makeText(BiddingActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(BiddingActivity.this, "Failed to parse error response", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(BiddingActivity.this, "Error placing bid", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}
