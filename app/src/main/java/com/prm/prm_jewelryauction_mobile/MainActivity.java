package com.prm.prm_jewelryauction_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prm.prm_jewelryauction_mobile.fragment.HomeFragment;
import com.prm.prm_jewelryauction_mobile.fragment.ProfileFragment; // Nếu bạn có ProfileFragment

public class MainActivity extends AppCompatActivity {
    private static final int NAV_HOME = R.id.nav_home;
    private static final int NAV_PROFILE = R.id.nav_profile; // Thêm ID cho profile nếu cần

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Sử dụng if-else để kiểm tra ID
            if (item.getItemId() == NAV_HOME) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == NAV_PROFILE) {
                selectedFragment = new ProfileFragment(); // Chỉ cần nếu bạn có ProfileFragment
            }

            // Thay thế Fragment
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}
