package com.prm.prm_jewelryauction_mobile.activity.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.data.request.UserSignInRequest;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.service.ApiAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etEmail = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserSignInRequest userSignInRequest = new UserSignInRequest(email, password);
        ApiAuthService apiService = RetrofitClient.getRetrofitInstance().create(ApiAuthService.class);
        Call<Void> call = apiService.signinUser(userSignInRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi gọi API: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
