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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.ProductAdapter.ProductAdapter;
import com.prm.prm_jewelryauction_mobile.adapter.ValuationAdapter.ValuationAdapter;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.Valuation;
import com.prm.prm_jewelryauction_mobile.model.ValuationResponse;
import com.prm.prm_jewelryauction_mobile.service.ApiProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValuationFragment extends Fragment {

    private RecyclerView recyclerView;
    private ValuationAdapter valuationAdapter;
    private List<Valuation> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_valuating, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.valuation_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList = new ArrayList<>();
        valuationAdapter = new ValuationAdapter(productList, requireContext());
        recyclerView.setAdapter(valuationAdapter);

        // Load valuations from API
        loadValuations();

        return view;
    }

    private void loadValuations() {
        // Get Retrofit instance with token handling
        ApiProduct apiProduct = RetrofitClient.getRetrofitInstanceWithToken(requireContext()).create(ApiProduct.class);

        // Call the API to fetch valuations
        Call<ValuationResponse> call = apiProduct.getValuation();
        call.enqueue(new Callback<ValuationResponse>() {
            @Override
            public void onResponse(Call<ValuationResponse> call, Response<ValuationResponse> response) {
                System.out.println("HI");
                System.out.println(response);
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    List<Valuation> valuations = response.body().getData();  // Extract the 'data' field
                    if (valuations != null) {
                        productList.addAll(valuations);
                        valuationAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No valuations found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to load valuations", Toast.LENGTH_SHORT).show();
                    Log.e("ValuationFragment", "Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ValuationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ValuationFragment", "API call failed", t);
            }
        });
    }
}
