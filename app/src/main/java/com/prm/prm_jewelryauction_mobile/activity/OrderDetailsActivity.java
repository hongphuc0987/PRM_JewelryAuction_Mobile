package com.prm.prm_jewelryauction_mobile.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.model.AuctionItem;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView jewelryName, winningPrice, auctionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Initialize views
        jewelryName = findViewById(R.id.jewelryName);
        winningPrice = findViewById(R.id.winningPrice);
        auctionDate = findViewById(R.id.auctionDate);
        ImageButton backButton = findViewById(R.id.backButton); // Get back button reference

        // Get auction item from Intent
        AuctionItem auctionItem = (AuctionItem) getIntent().getSerializableExtra("auctionItem");

        if (auctionItem != null) {
            jewelryName.setText(auctionItem.getName());
            winningPrice.setText("Winning Price: $" + auctionItem.getCurrentPrice());
            auctionDate.setText("Auction End Date: " + auctionItem.getEndTime());
        }

        // Set back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Finish the current activity and go back to the previous one
            }
        });
    }
}
