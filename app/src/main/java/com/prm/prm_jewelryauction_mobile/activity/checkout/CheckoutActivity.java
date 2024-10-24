package com.prm.prm_jewelryauction_mobile.activity.checkout;
import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.model.ApiResponseWinAuction;
import com.prm.prm_jewelryauction_mobile.model.CheckoutRequest;
import com.prm.prm_jewelryauction_mobile.service.impl.ApiOrderServiceImpl;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    private TextView itemName, money, winningPrice;
    ImageView jewelryImage;
    private EditText fullNameInput, phoneNumberInput, addressInput;
    private Button checkoutButton;
    private ApiOrderServiceImpl apiOrderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Get views
        itemName = findViewById(R.id.itemName);
        money = findViewById(R.id.money);
        jewelryImage = findViewById(R.id.jewelryImage);
        winningPrice = findViewById(R.id.winningPrice);
        fullNameInput = findViewById(R.id.fullNameInput);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        addressInput = findViewById(R.id.addressInput);
        checkoutButton = findViewById(R.id.checkoutButton);

        ImageButton backButton = findViewById(R.id.backButton); // Get back button reference

        // Disable checkout button initially
        checkoutButton.setEnabled(false);

        // Add TextWatchers to the input fields
        fullNameInput.addTextChangedListener(inputWatcher);
        phoneNumberInput.addTextChangedListener(inputWatcher);
        addressInput.addTextChangedListener(inputWatcher);

        // Get auctionId from Intent
        Long auctionId = getIntent().getLongExtra("auctionId", -1);
        String name = getIntent().getStringExtra("itemName");
        String currentPrice = getIntent().getStringExtra("currentPrice");
        String jewelryImg = getIntent().getStringExtra("jewelryImage");

        if (name != null) {
            itemName.setText(name);
        }
        if (currentPrice != null) {
            winningPrice.setText(String.format(currentPrice));
        }
        if (jewelryImg != null) {
            Picasso.get().load(jewelryImg).into(jewelryImage);  // Load image using Picasso
        } else {
            jewelryImage.setImageResource(R.drawable.ic_errorimage);  // Placeholder image
        }
        // Initialize ApiOrderServiceImpl
        apiOrderService = new ApiOrderServiceImpl(this);

        backButton.setOnClickListener(v -> finish());  // Finish the current activity and go back to the previous one

        // Set button click listener
        checkoutButton.setOnClickListener(v -> {
            String fullName = fullNameInput.getText().toString().trim();
            String phoneNumber = phoneNumberInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();

            // Create CheckoutRequest object
            CheckoutRequest checkoutRequest = new CheckoutRequest(auctionId, fullName, phoneNumber, address);

            // Call the checkout API
            apiOrderService.checkout(checkoutRequest).enqueue(new Callback<ApiResponseWinAuction>() {
                @Override
                public void onResponse(Call<ApiResponseWinAuction> call, Response<ApiResponseWinAuction> response) {
                    Log.d(TAG, call.request().url().toString());
                    Log.d(TAG, response.body().toString());
                    if (response.isSuccessful() && response.body().getCode() == 200) {
                        Toast.makeText(CheckoutActivity.this, "Checkout successful!", Toast.LENGTH_SHORT).show();
                        // Optionally, navigate to another screen or finish the activity
                    } else {
                        Toast.makeText(CheckoutActivity.this, "Checkout failed: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseWinAuction> call, Throwable t) {
                    Log.d(TAG, call.request().toString());
                    Toast.makeText(CheckoutActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // TextWatcher to monitor the input fields
    private final TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Check if all fields are filled
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    // Method to check if all input fields are filled
    private void checkFieldsForEmptyValues() {
        String fullName = fullNameInput.getText().toString().trim();
        String phoneNumber = phoneNumberInput.getText().toString().trim();
        String address = addressInput.getText().toString().trim();

        // Enable button if all fields are not empty
        checkoutButton.setEnabled(!fullName.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty());
    }
}
