package com.prm.prm_jewelryauction_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.prm.prm_jewelryauction_mobile.R;
import com.squareup.picasso.Picasso;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView jewelryName, winningPrice, auctionDate, winnerName, sellerName, auctionStatus;
    private ImageView jewelryImage;  // ImageView for displaying the jewelry image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Initialize views
        jewelryName = findViewById(R.id.jewelryName);
        winningPrice = findViewById(R.id.winningPrice);
        auctionDate = findViewById(R.id.auctionDate);
        winnerName = findViewById(R.id.winnerName);
        sellerName = findViewById(R.id.sellerName);
        auctionStatus = findViewById(R.id.auctionStatus);
        jewelryImage = findViewById(R.id.jewelryImage);  // Initialize ImageView
        ImageButton backButton = findViewById(R.id.backButton); // Get back button reference
        Button buttonCheckout = findViewById(R.id.checkoutButton);

        // Get data from Intent
        String jewelryNameValue = getIntent().getStringExtra("jewelryName");
        String currentPrice = getIntent().getStringExtra("currentPrice");
        String endTime = getIntent().getStringExtra("endTime");
        String winnerNameValue = getIntent().getStringExtra("winnerName");
        String sellerNameValue = getIntent().getStringExtra("sellerName");
        String status = getIntent().getStringExtra("status");
        String jewelryImageValue = getIntent().getStringExtra("jewelryImage");

        // Set values to the views
        if (jewelryNameValue != null) {
            jewelryName.setText(jewelryNameValue);
        }
        if (currentPrice != null) {
            winningPrice.setText(String.format("Winning Price: $%s", currentPrice));
        }
        if (endTime != null) {
            auctionDate.setText(String.format("Auction End Date: %s", endTime));
        }
        if (winnerNameValue != null) {
            winnerName.setText(String.format("Winner: %s", winnerNameValue));
        }
        if (sellerNameValue != null) {
            sellerName.setText(String.format("Seller: %s", sellerNameValue));
        }
        if (status != null) {
            auctionStatus.setText(String.format("Status: %s", status));
        }
        if (jewelryImageValue != null) {
            Picasso.get().load(jewelryImageValue).into(jewelryImage);  // Load image using Picasso
        } else {
            jewelryImage.setImageResource(R.drawable.ic_errorimage);  // Placeholder image
        }

        // Set back button click listener
        backButton.setOnClickListener(v -> finish());  // Finish the current activity and go back to the previous one

//        buttonCheckout.setOnClickListener(v -> {
//            // Start CheckoutActivity (create this activity if it doesn't exist)
//            Intent intent = new Intent(OrderDetailsActivity.this, CheckoutActivity.class);
//            // Optionally pass data needed for checkout
//            startActivity(intent);
//        });
    }
}
