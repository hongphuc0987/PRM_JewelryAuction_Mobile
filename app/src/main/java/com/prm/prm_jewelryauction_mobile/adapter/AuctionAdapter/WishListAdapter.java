package com.prm.prm_jewelryauction_mobile.adapter.AuctionAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.activity.auction.AuctionDetailActivity;
import com.prm.prm_jewelryauction_mobile.data.response.Wishlist;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private List<Wishlist> wishList;
    String baseUrl = "http://35.194.232.209:9090/uploads/jewelry/";


    public WishListAdapter(List<Wishlist> wishList) {
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wishlist wishlistItem = wishList.get(position);
        AuctionModel auction = wishlistItem.getAuction();

        if (auction != null) {
            holder.tvName.setText(auction.getJewelry().getName());
            holder.tvCurrentPrice.setText(String.valueOf(auction.getCurrentPrice()));
            Glide.with(holder.itemView.getContext())
                    .load(baseUrl + auction.getJewelry().getThumbnail())
                    .into(holder.ivThumbnail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AuctionDetailActivity.class);
                intent.putExtra("AUCTION_ID", auction.getId());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName;
        TextView tvCurrentPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.imgThumbnail);
            tvName = itemView.findViewById(R.id.tvName);
            tvCurrentPrice = itemView.findViewById(R.id.tvCurrentPrice);
        }
    }
}


