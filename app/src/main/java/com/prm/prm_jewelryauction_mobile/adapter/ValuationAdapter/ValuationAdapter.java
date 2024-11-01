package com.prm.prm_jewelryauction_mobile.adapter.ValuationAdapter;

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
import com.prm.prm_jewelryauction_mobile.activity.product_management.DetailValuating;
import com.prm.prm_jewelryauction_mobile.model.JewelryModel;
import com.prm.prm_jewelryauction_mobile.model.Product;
import com.prm.prm_jewelryauction_mobile.model.Valuation;

import java.util.List;

public class ValuationAdapter extends RecyclerView.Adapter<ValuationAdapter.ValuationViewHolder> {

    private List<Valuation> valuationList; // Danh sách sản phẩm
    private Context context; // Context để khởi tạo Intent
    String baseUrl = "http://35.194.232.209:9090/uploads/jewelry/";

    // Constructor
    public ValuationAdapter(List<Valuation> valuationList, Context context) {
        this.valuationList = valuationList;
        this.context = context; // Lưu context
    }

    // Tạo ViewHolder
    @NonNull
    @Override
    public ValuationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valuation, parent, false);
        return new ValuationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ValuationViewHolder holder, int position) {
        Valuation product = valuationList.get(position);
        holder.productName.setText(product.getJewelry().getName());
        holder.productImage.setImageResource(R.drawable.ic_complete); // Set image resource
        holder.productPrice.setText("Starting Price: " + product.getJewelry().getStaringPrice() + " VND");
        holder.productStatus.setText("Status: " + product.getStatus());
        Glide.with(context)
                .load(baseUrl + product.getJewelry().getThumbnail())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_errorimage)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.productImage);
//        holder.productStatus.setText(product.getStatus());
        // Thiết lập sự kiện nhấn vào item sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailValuating.class);
            intent.putExtra("VALUATION_ID",  product.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return valuationList.size();
    }

    // ViewHolder cho item sản phẩm
    public static class ValuationViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productStatus;

        public ValuationViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.valuation_image);
            productName = itemView.findViewById(R.id.valuation_name);
            productPrice = itemView.findViewById(R.id.valuation_startingPrice);
            productStatus = itemView.findViewById(R.id.valuation_status);
        }
    }
}
