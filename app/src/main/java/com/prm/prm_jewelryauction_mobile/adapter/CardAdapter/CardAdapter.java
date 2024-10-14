package com.prm.prm_jewelryauction_mobile.adapter.CardAdapter;

import android.content.Context; // Import Context
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.MainActivity;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.fragment.AuctionDetailFragment;
import com.prm.prm_jewelryauction_mobile.model.CardItem;

import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private final List<CardItem> cardItemList;
    private final Context context; // Thêm context

    public CardAdapter(Context context, List<CardItem> cardItemList) {
        this.context = context; // Gán context
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentItem = cardItemList.get(position);
        holder.cardImage.setImageResource(currentItem.getImageResource());
        holder.cardTitle.setText(currentItem.getTitle());
        holder.cardPrice.setText(currentItem.getPrice());
        holder.cardCountdown.setText(getCountdown(currentItem.getEndDate()));

        holder.itemView.setOnClickListener(v -> {
            AuctionDetailFragment detailFragment = AuctionDetailFragment.newInstance(currentItem.getImageResource(), currentItem.getTitle(), currentItem.getPrice());

            // Thay thế Fragment và giữ lại Bottom Navigation
            ((MainActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null) // Thêm vào back stack
                    .commit();
        });
    }

    private String getCountdown(long endDateMillis) {
        long currentTime = System.currentTimeMillis();
        long difference = endDateMillis - currentTime;

        if (difference < 0) {
            return "Expired"; // Countdown is finished
        }

        long days = difference / (1000 * 60 * 60 * 24);
        long hours = (difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (difference % (1000 * 60 * 60)) / (1000 * 60);

        return String.format(Locale.getDefault(), "%d days %d hours %d minutes left", days, hours, minutes);
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImage;
        public TextView cardTitle;
        public TextView cardPrice;
        public TextView cardCountdown;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardPrice = itemView.findViewById(R.id.card_price);
            cardCountdown = itemView.findViewById(R.id.card_countdown);
        }
    }
}
