package com.prm.prm_jewelryauction_mobile.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_product, container, false);

        // Khởi tạo RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo danh sách sản phẩm
        productList = new ArrayList<>();
        // Thêm sản phẩm mẫu vào danh sách
        productList.add(new Product("Sản phẩm 1", R.drawable.ic_launcher_background)); // Thay đổi với tên và ID hình ảnh thực
        productList.add(new Product("Sản phẩm 2", R.drawable.ic_launcher_background));

        // Khởi tạo adapter với context
        productAdapter = new ProductAdapter(productList, requireContext()); // Thay đổi ở đây
        recyclerView.setAdapter(productAdapter);

        return view;
    }
}
