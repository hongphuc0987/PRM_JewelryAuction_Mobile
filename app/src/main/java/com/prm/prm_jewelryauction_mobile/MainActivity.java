package com.prm.prm_jewelryauction_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prm.prm_jewelryauction_mobile.activity.auth.LoginActivity;
import com.prm.prm_jewelryauction_mobile.fragment.HomeFragment;
import com.prm.prm_jewelryauction_mobile.fragment.ProfileFragment; // Nếu bạn có ProfileFragment

public class MainActivity extends AppCompatActivity {
    private static final int NAV_HOME = R.id.nav_home;
    private static final int NAV_PROFILE = R.id.nav_profile;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isUserLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int navigateTo = intent.getIntExtra("navigateTo", NAV_HOME);

        if (savedInstanceState == null) {
            Fragment initialFragment = (navigateTo == NAV_PROFILE)
                    ? new ProfileFragment() : new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, initialFragment)
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == NAV_HOME) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == NAV_PROFILE) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
        String versionRelease = Build.VERSION.RELEASE; // Lấy phiên bản Android (ví dụ: "11")
        int sdkVersion = Build.VERSION.SDK_INT; // Lấy phiên bản SDK (ví dụ: 30 cho Android 11)

        // In ra log
        Log.d("AndroidVersion", "Version Release: " + versionRelease);
        Log.d("AndroidVersion", "SDK Version: " + sdkVersion);

    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        return accessToken != null;
    }
}
