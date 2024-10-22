package com.prm.prm_jewelryauction_mobile.activity.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.zalo_pay.Api.CreateOrder;

import org.json.JSONObject;

import java.util.concurrent.Executors;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentConfirm extends AppCompatActivity {
    TextView tvAmount;
    Button btnConfirm, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirm);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Initialize views
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        tvAmount = findViewById(R.id.tvPaymentAmount);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        String amount = intent.getStringExtra("amount");
        tvAmount.setText("Amount: " + amount);
        // Get the passed amount from the intent
        // Confirm button - Proceed to payment with ZaloPay
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(amount);
                    String code = data.getString("return_code");
                    if(code.equals("1")) {
                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(PaymentConfirm.this, token, "demozpdk://app", new PayOrderListener() {

                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                runOnUiThread(() -> {
                                    Intent intent1 = new Intent(PaymentConfirm.this, PaymentNotification.class);
                                    intent1.putExtra("result", "Payment Successfully");
                                    startActivity(intent1);
                                });
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                runOnUiThread(() -> {
                                    Intent intent1 = new Intent(PaymentConfirm.this, PaymentNotification.class);
                                    intent1.putExtra("result", "Payment Cancelled");
                                    startActivity(intent1);
                                });
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                runOnUiThread(() -> {
                                    Intent intent1 = new Intent(PaymentConfirm.this, PaymentNotification.class);
                                    intent1.putExtra("result", "Payment Cancelled");
                                    startActivity(intent1);
                                });
                            }
                        });
                    }
                } catch (Error | Exception error) {

                }
            }
        });

        // Cancel button - Return to previous screen
        btnCancel.setOnClickListener(v -> finish());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
