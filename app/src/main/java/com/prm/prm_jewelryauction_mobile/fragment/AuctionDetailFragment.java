package com.prm.prm_jewelryauction_mobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prm.prm_jewelryauction_mobile.R;

public class AuctionDetailFragment extends Fragment {

    private static final String ARG_IMAGE_RES_ID = "image_res_id";
    private static final String ARG_TITLE = "title";
    private static final String ARG_PRICE = "price";

    public static AuctionDetailFragment newInstance(int imageResId, String title, String price) {
        AuctionDetailFragment fragment = new AuctionDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PRICE, price);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auction_detail_fragment, container, false);

        // Nhận dữ liệu từ arguments
        int imageResId = getArguments().getInt(ARG_IMAGE_RES_ID);
        String title = getArguments().getString(ARG_TITLE);
        String price = getArguments().getString(ARG_PRICE);

        // Ánh xạ các View
        ImageView imageView = view.findViewById(R.id.detail_image);
        TextView titleTextView = view.findViewById(R.id.detail_title);
        TextView priceTextView = view.findViewById(R.id.detail_price);
        Button biddingButton = view.findViewById(R.id.button_bidding);
        Button backButton = view.findViewById(R.id.button_back);

        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Thiết lập dữ liệu vào các View
        imageView.setImageResource(imageResId);
        titleTextView.setText(title);
        priceTextView.setText(price);

        // Thiết lập sự kiện cho nút Bidding
        biddingButton.setOnClickListener(v -> {
            // Thực hiện hành động bidding ở đây
        });

        return view;
    }
}

