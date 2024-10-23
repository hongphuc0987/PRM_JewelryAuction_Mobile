package com.prm.prm_jewelryauction_mobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.prm.prm_jewelryauction_mobile.activity.auction.WinAuctionListActivity;  // Import your Order List activity
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.payment.PaymentActivity;
import com.prm.prm_jewelryauction_mobile.activity.auth.LoginActivity;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Find the icon container where icons will be dynamically added
        LinearLayout iconContainer = view.findViewById(R.id.icon_container);
        TextView walletBalance = view.findViewById(R.id.wallet_balance);
        Button btnDeposit = view.findViewById(R.id.button_deposit);
        walletBalance.setText("20000000");
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        // Add icons with names dynamically
        addIconWithName(iconContainer, R.drawable.ic_account, "Profile", () -> navigateToFragment(new HomeFragment()));
        addIconWithName(iconContainer, R.drawable.ic_management, "Product", () -> navigateToFragment(new HomeFragment()));
        addIconWithName(iconContainer, R.drawable.ic_order, "Order", () -> {
            Intent intent = new Intent(getActivity(), WinAuctionListActivity.class);
            startActivity(intent);
        });        addIconWithName(iconContainer, R.drawable.ic_add, "Add Product", () -> navigateToFragment(new AddJewelryFragment()));
        addIconWithName(iconContainer, R.drawable.ic_logout, "Logout", this::logout);
        return view;
    }
    private void addIconWithName(LinearLayout parent, int iconResId, String name, Runnable onClickAction) {
        // Create a vertical layout to hold the icon and name
        LinearLayout itemLayout = new LinearLayout(getContext());
        itemLayout.setOrientation(LinearLayout.VERTICAL);
        itemLayout.setGravity(Gravity.CENTER);
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        itemLayout.setPadding(40, 0, 40, 0);
        // Create an ImageView for the icon
        ImageView icon = new ImageView(getContext());
        icon.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        icon.setImageResource(iconResId);
        icon.setColorFilter(Color.BLACK);  // Optional: Tint the icon black
        icon.setPadding(20, 8, 20, 8);
        // Create a TextView for the icon name
        TextView iconName = new TextView(getContext());
        iconName.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        iconName.setText(name);
        iconName.setGravity(Gravity.CENTER);
        iconName.setTextColor(Color.BLACK);
        iconName.setTextSize(14);
        // Add the icon and name to the item layout
        itemLayout.addView(icon);
        itemLayout.addView(iconName);
        // Set an OnClickListener to handle icon click events
        itemLayout.setOnClickListener(view -> onClickAction.run());
        // Add the item layout to the parent container
        parent.addView(itemLayout);
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // Add to back stack to allow "Back" navigation
        transaction.commit();
    }
    private void logout() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void addJewelry() {

    }

}