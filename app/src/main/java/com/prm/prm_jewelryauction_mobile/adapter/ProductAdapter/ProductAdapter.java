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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.auction.AuctionDetailActivity;
import com.prm.prm_jewelryauction_mobile.activity.product_management.DetailActivity;
import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<JewelryModel> productList; // Danh sách sản phẩm
    private Context context; // Context để khởi tạo Intent
    String baseUrl = "http://35.194.232.209:9090/uploads/jewelry/";

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
        holder.productPrice.setText("Starting Price: " + product.getStaringPrice() + " VND");
        holder.productStatus.setText("Status: " + product.getStatus());
//        holder.productStatus.setText(product.getStatus());
        Glide.with(context)
                .load(baseUrl + product.getThumbnail())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_errorimage)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.productImage);
        // Thiết lập sự kiện nhấn vào item sản phẩm
        holder.itemView.setOnClickListener(v -> {
            System.out.println(product.getId());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("JEWELRY_ID",  product.getId());
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
            productStatus = itemView.findViewById(R.id.status);
        }
    }
}
