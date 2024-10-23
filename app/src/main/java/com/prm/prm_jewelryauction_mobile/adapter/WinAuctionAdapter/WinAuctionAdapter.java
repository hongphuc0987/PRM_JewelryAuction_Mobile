package com.prm.prm_jewelryauction_mobile.adapter.WinAuctionAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.prm.prm_jewelryauction_mobile.R;


import com.prm.prm_jewelryauction_mobile.activity.OrderDetailsActivity;
import com.prm.prm_jewelryauction_mobile.model.AuctionItem;

import java.util.List;

public class WinAuctionAdapter extends RecyclerView.Adapter<WinAuctionAdapter.AuctionViewHolder> {
    private static final String TAG = "WinAuctionListActivity";  // Log tag for this activity

    private List<AuctionItem> auctionList;
    private Context context;

    public WinAuctionAdapter(List<AuctionItem> auctionList, Context context) {
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
        AuctionItem item = auctionList.get(position);
        Log.d(TAG, "onBindViewHolder: "+item.getName());
        holder.jewelryName.setText(item.getName());
        holder.winningPrice.setText(String.format("Winning Price: $%.2f", item.getCurrentPrice())); // Update to current price
        holder.auctionDate.setText(String.format("Auction End Date: %s", item.getEndTime())); // Use end time
        // Navigate to order details when the item is clicked
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailsActivity.class);
            intent.putExtra("auctionItem", (CharSequence) item);  // Pass the auction item to the next screen
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return auctionList.size();
    }

    public static class AuctionViewHolder extends RecyclerView.ViewHolder {

        TextView jewelryName, winningPrice, auctionDate;
        CardView cardView;

        public AuctionViewHolder(@NonNull View itemView) {
            super(itemView);
            jewelryName = itemView.findViewById(R.id.jewelryName);
            winningPrice = itemView.findViewById(R.id.winningPrice);
            auctionDate = itemView.findViewById(R.id.auctionDate);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
