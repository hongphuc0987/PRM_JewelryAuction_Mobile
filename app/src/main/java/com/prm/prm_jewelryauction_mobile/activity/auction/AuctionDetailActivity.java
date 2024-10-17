package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionDetailActivity extends AppCompatActivity {

    private TextView tvJewelryName, tvCategoryName, tvMaterials, tvCollectionName,tvJewelryBrand;
    private TextView tvSellerName, tvWinnerName, tvJewelryCondition, tvCurrentPrice, tvStartingPrice, tvStatus;
    private ImageView imgThumbnail, imgJewelry1, imgJewelry2, imgJewelry3;
    private Button btnAuction, btnBack;

    String baseUrl = "http://10.0.2.2:8080/images/users/";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_detail);

        tvJewelryName = findViewById(R.id.tvJewelryName);
        tvCategoryName = findViewById(R.id.tvCategoryName);
        tvMaterials = findViewById(R.id.tvMaterials);
        tvCollectionName = findViewById(R.id.tvCollectionName);
        tvSellerName = findViewById(R.id.tvSellerName);
        tvWinnerName = findViewById(R.id.tvWinnerName);
        tvJewelryCondition = findViewById(R.id.tvJewelryCondition);
        tvJewelryBrand = findViewById(R.id.tvBrandName);
        tvCurrentPrice = findViewById(R.id.tvCurrentPrice);
        tvStartingPrice = findViewById(R.id.tvStartingPrice);
        tvStatus = findViewById(R.id.tvStatus);
        imgThumbnail = findViewById(R.id.imgThumbnail);
        imgJewelry1 = findViewById(R.id.imgJewelry1);
        imgJewelry2 = findViewById(R.id.imgJewelry2);
        imgJewelry3 = findViewById(R.id.imgJewelry3);
        btnAuction = findViewById(R.id.btnAuction);
        btnBack = findViewById(R.id.btnBack);

        long auctionId = getIntent().getLongExtra("AUCTION_ID", -1);
        if (auctionId != -1) {
            loadAuctionDetails(auctionId);
        }

        // Sự kiện nút quay lại
        btnBack.setOnClickListener(v -> onBackPressed());

        // Sự kiện nút đấu giá
//        btnAuction.setOnClickListener(v -> {
//            Intent intent = new Intent(AuctionDetailActivity.this, AuctionBidActivity.class);
//            intent.putExtra("AUCTION_ID", auctionId);
//            startActivity(intent);
//        });
    }

    private void loadAuctionDetails(long auctionId) {
        ApiAuctionService apiService = RetrofitClient.getRetrofitInstance().create(ApiAuctionService.class);
        Call<AuctionModel> call = apiService.getAuction(auctionId);

        call.enqueue(new Callback<AuctionModel>() {
            @Override
            public void onResponse(Call<AuctionModel> call, Response<AuctionModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuctionModel auction = response.body();
                    displayAuctionDetails(auction);
                }
            }

            @Override
            public void onFailure(Call<AuctionModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void displayAuctionDetails(AuctionModel auction) {
        tvJewelryName.setText(auction.getJewelry().getName());
        tvCategoryName.setText("Category: " + auction.getJewelry().getCategory().getName());

        StringBuilder materials = new StringBuilder("Materials: ");
        for (int i = 0; i < auction.getJewelry().getJewelryMaterials().size(); i++) {
            materials.append(auction.getJewelry().getJewelryMaterials().get(i).getMaterial().getName())
                    .append(" (").append(auction.getJewelry().getJewelryMaterials().get(i).getWeight()).append("g)");
            if (i < auction.getJewelry().getJewelryMaterials().size() - 1) {
                materials.append(", ");
            }
        }
        tvMaterials.setText(materials.toString());

        tvCollectionName.setText("Collection: " + auction.getJewelry().getCollection().getName());
        tvSellerName.setText("Seller: " + auction.getJewelry().getSellerId().getFullName());
        tvWinnerName.setText("Winner: " + (auction.getWinner() != null ? auction.getWinner().getFullName() : "No winner yet"));
        tvJewelryCondition.setText("Condition: " + auction.getJewelry().getJewelryCondition());
        tvJewelryBrand.setText("Brand: " + auction.getJewelry().getBrand().getName());
        tvCurrentPrice.setText("Current Price: " + auction.getCurrentPrice() + " VND");
        tvStartingPrice.setText("Starting Price: " + auction.getJewelry().getStaringPrice() + " VND");
        tvStatus.setText("Status: " + auction.getStatus());

        Glide.with(this)
                .load(baseUrl + auction.getJewelry().getThumbnail())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_errorimage)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imgThumbnail);

        if (auction.getJewelry().getJewelryImages() != null && auction.getJewelry().getJewelryImages().size() > 0) {
            for (int i = 0; i < auction.getJewelry().getJewelryImages().size(); i++) {
                String imageUrl = baseUrl + auction.getJewelry().getJewelryImages().get(i).getUrl();
                switch (i) {
                    case 0:
                        Glide.with(this)
                                .load(imageUrl)
                                .apply(new RequestOptions()
                                        .error(R.drawable.ic_errorimage)
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(imgJewelry1);
                        break;
                    case 1:
                        Glide.with(this)
                                .load(imageUrl)
                                .apply(new RequestOptions()
                                        .error(R.drawable.ic_errorimage)
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(imgJewelry2);
                        break;
                    case 2:
                        Glide.with(this)
                                .load(imageUrl)
                                .apply(new RequestOptions()
                                        .error(R.drawable.ic_errorimage)
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(imgJewelry3);
                        break;
                }
            }
        }
    }
}
