package com.prm.prm_jewelryauction_mobile.activity.product_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.R;

public class DetailActivity extends AppCompatActivity {

    private TextView detailTextView;
    private Button editProductButton;
    private Button deleteProductButton; // Khai báo nút xóa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        detailTextView = findViewById(R.id.product_name_display);
        editProductButton = findViewById(R.id.edit_product_button);
        deleteProductButton = findViewById(R.id.delete_product_button); // Gán nút xóa từ layout

        // Lấy thông tin sản phẩm từ Intent
        String productName = getIntent().getStringExtra("PRODUCT_NAME");
        detailTextView.setText("Chi tiết sản phẩm: " + productName);

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
}
