package com.prm.prm_jewelryauction_mobile.activity.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.MainActivity;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.service.ApiWallet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentNotification extends AppCompatActivity {
    TextView txtNotification, txtAmount;
    ImageView imgStatus;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_result);

        txtNotification = findViewById(R.id.textViewNotify);
        txtAmount = findViewById(R.id.txtViewAmount);
        imgStatus = findViewById(R.id.imgStatus);
        btnBack = findViewById(R.id.btnBackToMain);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        String amount = intent.getStringExtra("amount");  // Get amount from Intent

        txtNotification.setText(result);
        txtAmount.setText("Amount Deposit: " + amount);

        // Set icon based on result
        if (result != null) {
            switch (result) {
                case "Payment Successfully":
                    imgStatus.setImageResource(R.drawable.ic_complete); // Success icon
                    // Call API to deposit amount
                    if (amount != null) {
                        addDepositToWallet(Long.parseLong(amount));
                    }
                    break;
                case "Payment Cancelled":
                case "Payment Failed":
                default:
                    imgStatus.setImageResource(R.drawable.ic_failed); // Failure icon
                    break;
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentNotification.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    // Method to call the deposit API
    private void addDepositToWallet(long amount) {
        ApiWallet apiWallet = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiWallet.class);
        Call<Void> call = apiWallet.depositWallet(amount);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PaymentNotification.this, "Deposit successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PaymentNotification.this, "Failed to deposit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PaymentNotification.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
