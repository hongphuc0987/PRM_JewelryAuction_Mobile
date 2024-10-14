package com.prm.prm_jewelryauction_mobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.CardAdapter.CardAdapter;
import com.prm.prm_jewelryauction_mobile.model.CardItem;
import com.prm.prm_jewelryauction_mobile.utils.GridDecorationSpacing.GridDecorationSpacing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<CardItem> cardItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        // Set GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set spacing for the grid
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        recyclerView.addItemDecoration(new GridDecorationSpacing(2, spacingInPixels, true));

        cardItemList = new ArrayList<>();
        loadCardItems();

        cardAdapter = new CardAdapter(cardItemList);
        recyclerView.setAdapter(cardAdapter);

        return view;
    }

    private void loadCardItems() {
        // Adding sample cards with expiration dates
        cardItemList.add(new CardItem(R.drawable.ic_home, "Item 1", "$10.00", getExpirationDate(1))); // Expires in 1 day
        cardItemList.add(new CardItem(R.drawable.ic_account, "Item 2", "$15.00", getExpirationDate(2))); // Expires in 2 days
        cardItemList.add(new CardItem(R.drawable.ic_home, "Item 3", "$20.00", getExpirationDate(3))); // Expires in 3 days
        cardItemList.add(new CardItem(R.drawable.ic_account, "Item 4", "$25.00", getExpirationDate(4))); // Expires in 4 days
        cardItemList.add(new CardItem(R.drawable.ic_home, "Item 5", "$30.00", getExpirationDate(5))); // Expires in 5 days
        cardItemList.add(new CardItem(R.drawable.ic_account, "Item 6", "$35.00", getExpirationDate(6))); // Expires in 6 days
        // Add more items as needed
    }

    private long getExpirationDate(int daysFromNow) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysFromNow); // Add days to the current date
        return calendar.getTimeInMillis(); // Return the time in milliseconds
    }
}

