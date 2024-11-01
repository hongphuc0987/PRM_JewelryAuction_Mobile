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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.widget.SearchView;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<AuctionModel> auctionList; // Store the full list of auctions
    private List<AuctionModel> filteredList; // Store the filtered list

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

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
                    auctionList = response.body(); // Store the full list
                    filteredList = new ArrayList<>(auctionList); // Initialize the filtered list
                    cardAdapter = new CardAdapter(requireActivity(), filteredList);
                    recyclerView.setAdapter(cardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<AuctionModel>> call, Throwable t) {
                Log.e("API_ERROR", "Error calling API: " + t.getMessage());
                Toast.makeText(getActivity(), "Connection error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filter(String query) {
        filteredList.clear(); // Clear the current filtered list
        if (query.isEmpty()) {
            filteredList.addAll(auctionList); // If query is empty, add all items
        } else {
            for (AuctionModel auction : auctionList) {
                if (auction.getJewelry().getName().toLowerCase().contains(query.toLowerCase())) { // Assuming you have a getProductName() method
                    filteredList.add(auction);
                }
            }
        }
        cardAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
    }
}
