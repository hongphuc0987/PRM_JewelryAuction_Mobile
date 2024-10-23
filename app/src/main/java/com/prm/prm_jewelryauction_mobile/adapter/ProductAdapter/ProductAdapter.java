package com.prm.prm_jewelryauction_mobile.adapter.ProductAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.product_management.DetailActivity;
import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<JewelryModel> productList; // Danh sách sản phẩm
    private Context context; // Context để khởi tạo Intent

    // Constructor
    public ProductAdapter(List<JewelryModel> productList, Context context) {
        this.productList = productList;
        this.context = context; // Lưu context
    }

    // Tạo ViewHolder
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        JewelryModel product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productImage.setImageResource(R.drawable.ic_complete); // Set image resource
        holder.productPrice.setText("Starting Price: " + product.getStaringPrice());
//        holder.productStatus.setText(product.getStatus());

        // Thiết lập sự kiện nhấn vào item sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("PRODUCT_NAME", product.getName()); // Chuyển thông tin sản phẩm
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder cho item sản phẩm
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productStatus;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.startingProductMe);
            productPrice = itemView.findViewById(R.id.status);
        }
    }
}
