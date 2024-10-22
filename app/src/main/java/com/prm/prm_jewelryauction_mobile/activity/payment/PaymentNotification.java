package com.prm.prm_jewelryauction_mobile.activity.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.MainActivity;
import com.prm.prm_jewelryauction_mobile.R;

public class PaymentNotification extends AppCompatActivity {
    TextView txtNotification;
    ImageView imgStatus;  // Declare ImageView for the status icon
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_result);

        txtNotification = findViewById(R.id.textViewNotify);
        imgStatus = findViewById(R.id.imgStatus);  // Initialize the ImageView
        btnBack = findViewById(R.id.btnBackToMain);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        txtNotification.setText(result);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentNotification.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        // Set the icon based on the result
        if (result != null) {
            switch (result) {
                case "Payment Successfully":
                    imgStatus.setImageResource(R.drawable.ic_complete); // Success icon
                    break;
                case "Payment Cancelled":
                    imgStatus.setImageResource(R.drawable.ic_failed); // Cancel icon
                    break;
                case "Payment Failed":
                    imgStatus.setImageResource(R.drawable.ic_failed); // Failure icon
                    break;
                default:
                    imgStatus.setImageResource(R.drawable.ic_failed); // Default or unknown status icon
                    break;
            }
        }
    }
}
