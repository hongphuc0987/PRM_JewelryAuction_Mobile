package com.prm.prm_jewelryauction_mobile.adapter.CardAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.model.AuctionList;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private Context context;
    private List<AuctionModel> auctionList;

    public CardAdapter(Context context, List<AuctionModel> auctionList) {
        this.context = context;
        this.auctionList = auctionList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_auction, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        AuctionModel auction = auctionList.get(position);
//        System.out.println(auction.getJewelry().getName());
//
        holder.tvJewelryName.setText(auction.getJewelry().getName());
        holder.tvCurrentPrice.setText(auction.getCurrentPrice());
        holder.tvStatus.setText(auction.getStatus());

    }

    @Override
    public int getItemCount() {
        return auctionList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView tvJewelryName, tvCurrentPrice, tvStatus, tvEndTime;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            tvJewelryName = itemView.findViewById(R.id.tvJewelryName);
            tvCurrentPrice = itemView.findViewById(R.id.tvCurrentPrice);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
        }
    }
}
