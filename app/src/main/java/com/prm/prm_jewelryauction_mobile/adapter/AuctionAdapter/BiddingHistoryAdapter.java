package com.prm.prm_jewelryauction_mobile.adapter.AuctionAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.data.response.BiddingHistory;

import java.util.List;

public class BiddingHistoryAdapter extends RecyclerView.Adapter<BiddingHistoryAdapter.ViewHolder> {
    private final List<BiddingHistory> biddingHistoryList;

    public BiddingHistoryAdapter(List<BiddingHistory> biddingHistoryList) {
        this.biddingHistoryList = biddingHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bid_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BiddingHistory history = biddingHistoryList.get(position);
        holder.tvUserName.setText("User: " + history.getUserName());
        holder.tvBidAmount.setText("Bid Amount: " + history.getBidAmount() + " VND");
        holder.tvStatus.setText("Status: " + history.getStatus());
        holder.tvBidTime.setText("Time: " + history.getBidTime());
    }

    @Override
    public int getItemCount() {
        return biddingHistoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName, tvBidAmount, tvStatus, tvBidTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvBidAmount = itemView.findViewById(R.id.tvBidAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvBidTime = itemView.findViewById(R.id.tvBidTime);
        }
    }
}