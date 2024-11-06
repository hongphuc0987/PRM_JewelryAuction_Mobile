package com.prm.prm_jewelryauction_mobile.activity.auction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.AuctionAdapter.MyAuctionAdapter;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.data.response.MyAuctionResponse;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAuctionFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAuctionAdapter adapter;
    private ProgressBar progressBar;
    private Button btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_wishlist, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewWishList);
        progressBar = view.findViewById(R.id.progressBar);
        btnBack = view.findViewById(R.id.btnBack);

        // Ẩn nút Back
        btnBack.setVisibility(View.GONE);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchWishList();

        return view;
    }

    private void fetchWishList() {
        ApiAuctionService apiService = RetrofitClient.getRetrofitInstanceWithToken(requireContext()).create(ApiAuctionService.class);
        Call<MyAuctionResponse> call = apiService.myAuction();

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<MyAuctionResponse>() {
            @Override
            public void onResponse(Call<MyAuctionResponse> call, Response<MyAuctionResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new MyAuctionAdapter(response.body().getData());
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("MyAuctionFragment", "Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<MyAuctionResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("MyAuctionFragment", "Error: " + t.getMessage());
            }
        });
    }
}
