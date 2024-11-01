package com.prm.prm_jewelryauction_mobile.activity.product_management;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.auction.BiddingActivity;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.ValuationRequest;
import com.prm.prm_jewelryauction_mobile.service.ApiProduct;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView txtName, txtDesc, txtColor, txtCondition, txtSex, txtSize, txtWeight
            , txtPrice, txtStatus, txtBrand, txtCategory, txtCollection, txtSeller;
    private Button editProductButton;
    private Button deleteProductButton, valuationButton; // Khai báo nút xóa
    private ImageView imageThumbnail;
    String baseUrl = "http://35.194.232.209:9090/uploads/jewelry/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        int productId = getIntent().getIntExtra("JEWELRY_ID", -1);
        if(productId != -1) {
            loadJewelryDetail(productId);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable Back Button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Product Details");
        }

        // Tooltip for Back Button
        View backButton = toolbar.getChildAt(0); // First child is usually the back button
        TooltipCompat.setTooltipText(backButton, "Go Back");

        // Handle Back Button Click
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Initialize Views
        txtName = findViewById(R.id.product_name_display);
        txtDesc = findViewById(R.id.product_description_display);
        txtColor = findViewById(R.id.product_color_display);
        txtCondition = findViewById(R.id.product_condition_display);
        txtSex = findViewById(R.id.product_sex_display);
        txtSize = findViewById(R.id.product_size_display);
        txtWeight = findViewById(R.id.product_weight_display);
        txtPrice = findViewById(R.id.product_starting_price_display);
        txtStatus = findViewById(R.id.product_status_display);
        txtBrand = findViewById(R.id.product_brand_id_display);
        txtCategory = findViewById(R.id.product_category_id_display);
        txtCollection = findViewById(R.id.product_collection_id_display);
        txtSeller = findViewById(R.id.seller_id_display);
        editProductButton = findViewById(R.id.edit_product_button);
        deleteProductButton = findViewById(R.id.delete_product_button); // Gán nút xóa từ layout
        valuationButton = findViewById(R.id.valuation_button);
        imageThumbnail = findViewById(R.id.product_image);
        // Lấy thông tin sản phẩm từ Intent

        // Thiết lập sự kiện click cho nút Edit
        editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        // Thiết lập sự kiện click cho nút Delete
        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        valuationButton.setOnClickListener(v -> {
            showValuationEditDialog();
        });
    }

    private void showEditDialog() {
        // Tạo LayoutInflater để tạo view cho dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_product, null);

        // Khởi tạo các EditText trong dialog
        EditText productNameEditText = dialogView.findViewById(R.id.product_name);
        EditText productDescriptionEditText = dialogView.findViewById(R.id.product_description);
        EditText productColorEditText = dialogView.findViewById(R.id.product_color);

        // Tạo AlertDialog cho việc chỉnh sửa
        new AlertDialog.Builder(this)
                .setTitle("Chỉnh sửa sản phẩm")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    // Xử lý lưu dữ liệu sau khi nhấn nút Lưu (nếu cần)
                    String updatedName = productNameEditText.getText().toString();
                    String updatedDescription = productDescriptionEditText.getText().toString();
                    String updatedColor = productColorEditText.getText().toString();
                    // Cập nhật dữ liệu cho sản phẩm nếu cần
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void showDeleteConfirmationDialog() {
        // Tạo AlertDialog xác nhận xóa
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    // Xử lý xóa sản phẩm
                    // Thực hiện hành động xóa ở đây (gọi API hoặc cập nhật danh sách)
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void loadJewelryDetail(int id) {
        ApiProduct apiService = RetrofitClient.getRetrofitInstance().create(ApiProduct.class);
        Call<JewelryModel> call = apiService.getProductById(id);

        call.enqueue(new Callback<JewelryModel>() {
            @Override
            public void onResponse(Call<JewelryModel> call, Response<JewelryModel> response) {
                System.out.println(response);
                if (response.isSuccessful() && response.body() != null) {
                    JewelryModel jewelry = response.body();
                    displayDetails(jewelry);
                }
            }

            @Override
            public void onFailure(Call<JewelryModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void displayDetails(JewelryModel jewelry) {
        txtName.setText(jewelry.getName());
        txtCategory.setText("Category: " + jewelry.getCategory().getName());
        txtDesc.setText("Description: " + jewelry.getDescription());
        txtColor.setText("Color: " + jewelry.getColor());
        txtCondition.setText("Condition: " + jewelry.getJewelryCondition());
        txtSeller.setText("Seller: " + jewelry.getSellerId().getFullName());
        txtCollection.setText("Collection: " + jewelry.getCollection().getName());
        txtBrand.setText("Brand: " + jewelry.getBrand().getName());
        txtStatus.setText("Status: " + jewelry.getStatus());
        txtSize.setText("Size: " + jewelry.getSize());
        txtSex.setText("Sex: " + jewelry.getSex());
        txtPrice.setText("Price: " + jewelry.getStaringPrice());
        txtWeight.setText("Weight: " + jewelry.getWeight());
        Glide.with(this)
                .load(baseUrl + jewelry.getThumbnail())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_errorimage)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageThumbnail);
    }


    private void showValuationEditDialog() {
        // Inflate the dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_valuation, null);

        // Get references to the EditText fields
        EditText notesEditText = dialogView.findViewById(R.id.valuation_notes);
        EditText addressEditText = dialogView.findViewById(R.id.valuation_address);
        EditText desiredPriceEditText = dialogView.findViewById(R.id.desired_price);
        Spinner valuationMethodSpinner = dialogView.findViewById(R.id.valuation_method_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.valuation_methods,  // Define this array in strings.xml
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valuationMethodSpinner.setAdapter(adapter);
        // Create the AlertDialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        // Get references to the buttons in the dialog
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button submitButton = dialogView.findViewById(R.id.submit_button);

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });

        submitButton.setOnClickListener(v -> {
            String notes = notesEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String desiredPriceStr = desiredPriceEditText.getText().toString().trim();
            String valuationMethod = valuationMethodSpinner.getSelectedItem().toString();

            if (address.isEmpty()) {
                addressEditText.setError("Address is required.");
                addressEditText.requestFocus();
            } else if (desiredPriceStr.isEmpty()) {
                desiredPriceEditText.setError("Desired Price is required.");
                desiredPriceEditText.requestFocus();
            } else {
                int desiredPrice = Integer.parseInt(desiredPriceStr);
                handleValuationUpdate(notes, address, desiredPrice, valuationMethod);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void handleValuationUpdate(String notes, String address, int desiredPrice, String valuationMethod) {
        // Use the entered notes and address (e.g., send to API or update UI)
        int jewelryId = getIntent().getIntExtra("JEWELRY_ID", -1);  // Get the product ID

        ValuationRequest valuationRequest = new ValuationRequest(
                jewelryId,
                desiredPrice,
                "VNPAY",
                notes,
                valuationMethod,
                address,
                false
        );
        System.out.println(valuationRequest.toString());
        ApiProduct apiProduct = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiProduct.class);
        Call<ResponseBody> call = apiProduct.valuationProduct(valuationRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response);
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Require valuation success", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        // Extract the error message from the response body
                        Toast.makeText(DetailActivity.this, "You dont have enought money", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(DetailActivity.this, "Error reading response", Toast.LENGTH_SHORT).show();
                    }                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
