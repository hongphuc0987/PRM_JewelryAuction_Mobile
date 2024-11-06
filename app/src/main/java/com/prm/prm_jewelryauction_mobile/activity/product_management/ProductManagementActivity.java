package com.prm.prm_jewelryauction_mobile.activity.product_management;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.auction.MyAuctionFragment;
import com.prm.prm_jewelryauction_mobile.fragment.ProductFragment;
import com.prm.prm_jewelryauction_mobile.fragment.ValuationFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductManagementActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_management);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ProductFragment();
                    case 1:
                        return new ValuationFragment();
                    case 2:
                        return new MyAuctionFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Sản Phẩm";
                    case 1:
                        return "Định giá";
                    case 2:
                        return "Đấu giá của tôi";
                    default:
                        return null;
                }
            }
        };

        viewPager.setAdapter(adapter);
    }
}

