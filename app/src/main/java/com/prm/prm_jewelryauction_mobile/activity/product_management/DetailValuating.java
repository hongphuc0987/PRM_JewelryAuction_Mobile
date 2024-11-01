package com.prm.prm_jewelryauction_mobile.activity.product_management;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.TooltipCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.data.request.auction.CreateAuctionRequest;
import com.prm.prm_jewelryauction_mobile.model.Valuation;
import com.prm.prm_jewelryauction_mobile.model.ValuationDetailResponse;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;
import com.prm.prm_jewelryauction_mobile.service.ApiJewelryService;
import com.prm.prm_jewelryauction_mobile.service.ApiProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailValuating extends AppCompatActivity {
    private ImageView productImage;
    private TextView productNameDisplay, valuationValue, desiredPrice, startingPrice, sellerIdDisplay;
    private Button valuationButton;
    private Calendar startTimeCalendar = Calendar.getInstance();
    private Calendar endTimeCalendar = Calendar.getInstance();
    String baseUrl = "http://35.194.232.209:9090/uploads/jewelry/";

    Valuation valuation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valuating_detail);

        int productId = getIntent().getIntExtra("VALUATION_ID", -1);

        setupToolbar();
        initializeViews();

        loadValuationDetail(productId);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_valuating);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Product Details");
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initializeViews() {
        productImage = findViewById(R.id.product_image);
        productNameDisplay = findViewById(R.id.product_name_display);
        valuationValue = findViewById(R.id.valuation_value);
        desiredPrice = findViewById(R.id.desired_price);
        startingPrice = findViewById(R.id.starting_price);
        sellerIdDisplay = findViewById(R.id.seller_id_display);
        valuationButton = findViewById(R.id.valuation_button);

        valuationButton.setOnClickListener(v -> openAuctionDialog());
    }

    private void openAuctionDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_auction, null);

        TextView textStartTime = dialogView.findViewById(R.id.text_start_time);
        TextView textEndTime = dialogView.findViewById(R.id.text_end_time);
        Button buttonStartTime = dialogView.findViewById(R.id.button_start_time);
        Button buttonEndTime = dialogView.findViewById(R.id.button_end_time);
        EditText editStep= dialogView.findViewById(R.id.edit_step);
        Button buttonConfirm = dialogView.findViewById(R.id.button_confirm);
        double startingPriceValue = valuation.getStartingPrice();
        float step = (float) (startingPriceValue * 0.05);

        editStep.setText(String.valueOf(step));
        editStep.setEnabled(false);

        buttonStartTime.setOnClickListener(v -> showDateTimePicker(startTimeCalendar, textStartTime));
        buttonEndTime.setOnClickListener(v -> showDateTimePicker(endTimeCalendar, textEndTime));

        buttonConfirm.setOnClickListener(v -> {
            if (validateAuctionInput()) {
//                double startingPriceValue = valuation.getStartingPrice();
//                float step = (float) (startingPriceValue * 0.05);
                editStep.setText(String.valueOf(step));
                editStep.setEnabled(false);
                editStep.setFocusable(false);

                String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                        .format(startTimeCalendar.getTime());
                String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                        .format(endTimeCalendar.getTime());

                CreateAuctionRequest auctionRequest = new CreateAuctionRequest(
                        (long) valuation.getJewelry().getId(), startTime, endTime, step);

                ApiAuctionService apiService = RetrofitClient.getRetrofitInstanceWithToken(this)
                        .create(ApiAuctionService.class);
                Call<Void> call = apiService.createAuction(auctionRequest);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailValuating.this, "Auction Created Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailValuating.this, "Failed to Create Auction", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DetailValuating.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.show();
    }

    private void showDateTimePicker(Calendar calendar, TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    showTimePicker(calendar, textView);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePicker(Calendar calendar, TextView textView) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                    textView.setText(format.format(calendar.getTime()));
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private boolean validateAuctionInput() {


        if (startTimeCalendar.before(Calendar.getInstance())) {
            Toast.makeText(this, "Start time must be in the future", Toast.LENGTH_SHORT).show();
            return false;
        }

        long difference = endTimeCalendar.getTimeInMillis() - startTimeCalendar.getTimeInMillis();
        if (difference < 0 || difference > 7 * 24 * 60 * 60 * 1000) { // 7 days in milliseconds
            Toast.makeText(this, "End time must be within 7 days of start time", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void loadValuationDetail(int id) {
        ApiProduct apiService = RetrofitClient.getRetrofitInstance().create(ApiProduct.class);
        Call<ValuationDetailResponse> call = apiService.getValuationByID(id);

        call.enqueue(new Callback<ValuationDetailResponse>() {
            @Override
            public void onResponse(Call<ValuationDetailResponse> call, Response<ValuationDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    valuation = response.body().getData();
                    displayDetails(valuation);
                } else {
                    Toast.makeText(DetailValuating.this, "Failed to load details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValuationDetailResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailValuating.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayDetails(Valuation valuation) {
        productNameDisplay.setText("Product Name: " + valuation.getJewelry().getName());
        valuationValue.setText("Valuation value: $" + valuation.getValuation_value());
        desiredPrice.setText("Desired Price: $" + valuation.getDesiredPrice());
        startingPrice.setText("Starting Price: $" + valuation.getStartingPrice());
        sellerIdDisplay.setText("Seller: " + valuation.getJewelry().getSellerId().getFullName());
        Glide.with(this)
                .load(baseUrl + valuation.getJewelry().getThumbnail())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_errorimage)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(productImage);
    }
}
