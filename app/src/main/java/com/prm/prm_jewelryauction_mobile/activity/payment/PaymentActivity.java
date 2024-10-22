package com.prm.prm_jewelryauction_mobile.activity.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.zalo_pay.Api.CreateOrder;

import org.json.JSONObject;

import java.util.concurrent.Executors;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentActivity extends AppCompatActivity {
    EditText depositAmount;
    Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
        depositAmount = findViewById(R.id.editTextPrice);
        btnSubmit = findViewById(R.id.buttonSubmitDeposit);

        btnSubmit.setOnClickListener(v -> {
            String amountStr = depositAmount.getText().toString().trim();
            if (TextUtils.isEmpty(amountStr)) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass the entered amount to the confirmation screen
            Intent intent = new Intent(PaymentActivity.this, PaymentConfirm.class);
            intent.putExtra("amount", amountStr);
            startActivity(intent);
        });
    }
}
