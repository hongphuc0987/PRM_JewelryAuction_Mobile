package com.prm.prm_jewelryauction_mobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.CardAdapter.CardAdapter;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.service.ApiAuctionService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        fetchAuctions();

        return view;
    }

    private void fetchAuctions() {
        ApiAuctionService apiService = RetrofitClient.getRetrofitInstance().create(ApiAuctionService.class);
        Call<List<AuctionModel>> call = apiService.getAuctionList();

        call.enqueue(new Callback<List<AuctionModel>>() {
            @Override
            public void onResponse(Call<List<AuctionModel>> call, Response<List<AuctionModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AuctionModel> auctionList = response.body();
                    System.out.println(response);
                    cardAdapter = new CardAdapter(requireActivity(), auctionList);
                    recyclerView.setAdapter(cardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AuctionModel>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi gọi API: " + t.getMessage());
                Toast.makeText(getActivity(), "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
