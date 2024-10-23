package com.prm.prm_jewelryauction_mobile.adapter.WinAuctionAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.prm.prm_jewelryauction_mobile.R;


import com.prm.prm_jewelryauction_mobile.activity.OrderDetailsActivity;
import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class WinAuctionAdapter extends RecyclerView.Adapter<WinAuctionAdapter.AuctionViewHolder> {
    private static final String TAG = "WinAuctionListActivity";  // Log tag for this activity

    private List<AuctionModel> auctionList;
    private Context context;

    public WinAuctionAdapter(List<AuctionModel> auctionList, Context context) {
        this.auctionList = auctionList;
        this.context = context;
    }

    @NonNull
    @Override
    public AuctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_win_auction, parent, false);
        return new AuctionViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull AuctionViewHolder holder, int position) {
        AuctionModel item = auctionList.get(position);
        Log.d(TAG, "onBindViewHolder: "+item.getWinner());
        holder.jewelryName.setText(item.getJewelry().getName());
        holder.winningPrice.setText((String.format("Winning Price: %s", item.getCurrentPrice()))); // Update to current price
        holder.auctionDate.setText(String.format("Auction End Date: %s", item.getEndTime())); // Use end time
        if (item.getJewelry().getJewelryImages() != null && !item.getJewelry().getJewelryImages().isEmpty()) {
            Picasso.get().load(item.getJewelry().getJewelryImages().get(0).getUrl()).into(holder.jewelryImage);
        } else {
            holder.jewelryImage.setImageResource(R.drawable.ic_errorimage);  // Placeholder image
        }
        // Navigate to order details when the item is clicked
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailsActivity.class);
            intent.putExtra("auctionId", item.getId());
            intent.putExtra("jewelryName", item.getJewelry().getName());
            intent.putExtra("jewelryImage", item.getJewelry().getJewelryImages() != null && !item.getJewelry().getJewelryImages().isEmpty() ?
                    item.getJewelry().getJewelryImages().get(0).getUrl() : null);
            intent.putExtra("startTime", item.getStartTime());
            intent.putExtra("endTime", item.getEndTime());
            intent.putExtra("currentPrice", item.getCurrentPrice());
            intent.putExtra("winnerName", item.getWinner() != null ? item.getWinner().getFullName() : null);
            intent.putExtra("sellerName", item.getJewelry() != null ? item.getJewelry().getSellerId().getFullName() : null);
            intent.putExtra("status", item.getStatus());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return auctionList.size();
    }

    public static class AuctionViewHolder extends RecyclerView.ViewHolder {

        TextView jewelryName, winningPrice, auctionDate;
        ImageView jewelryImage;  // Added ImageView for jewelry image
        CardView cardView;

        public AuctionViewHolder(@NonNull View itemView) {
            super(itemView);
            jewelryName = itemView.findViewById(R.id.jewelryName);
            winningPrice = itemView.findViewById(R.id.winningPrice);
            auctionDate = itemView.findViewById(R.id.auctionDate);
            cardView = itemView.findViewById(R.id.cardView);
            jewelryImage = itemView.findViewById(R.id.jewelryImage);  // Initialize ImageView

        }
    }
}
