package com.prm.prm_jewelryauction_mobile.activity.product_management;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.fragment.ProductFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                        return new ProductFragment(); // Trả về fragment cho tab "Sản Phẩm"
                    case 1:
                        return new ProductFragment(); // Tạo fragment cho tab "Đấu Giá"
                    case 2:
                        return new ProductFragment(); // Tạo fragment cho tab "Đấu Giá Thắng"
                    case 3:
                        return new ProductFragment(); // Tạo fragment cho tab "Giao Dịch Của Tôi"
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4; // Số lượng các tab
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Sản Phẩm";
                    case 1:
                        return "Đấu Giá";
                    case 2:
                        return "Đấu Giá Thắng";
                    case 3:
                        return "Giao Dịch Của Tôi";
                    default:
                        return null;
                }
            }
        };

        viewPager.setAdapter(adapter);
    }

    private class CustomPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        @Override
        public int getCount() {
            // Số lượng các tab
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(ProductManagementActivity.this);
            View layout = null;

            // Hiển thị các layout khác nhau cho từng tab
            switch (position) {
                case 0:
                    layout = inflater.inflate(R.layout.view_product, container, false);
                    break;
                case 1:
                    layout = inflater.inflate(R.layout.view_auction, container, false);
                    break;
                case 2:
                    layout = inflater.inflate(R.layout.view_won_auction, container, false);
                    break;
                case 3:
                    layout = inflater.inflate(R.layout.view_my_transactions, container, false);
                    break;
            }

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Sản Phẩm";
                case 1:
                    return "Đấu Giá";
                case 2:
                    return "Đấu Giá Thắng";
                case 3:
                    return "Giao Dịch Của Tôi";
            }
            return null;
        }
    }
}

