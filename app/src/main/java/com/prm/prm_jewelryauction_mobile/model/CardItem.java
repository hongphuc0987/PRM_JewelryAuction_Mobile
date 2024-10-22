package com.prm.prm_jewelryauction_mobile.model;
import java.util.Date;

public class CardItem {
    private final int imageResource;
    private final String title;
    private final String price;
    private final long endDate;

    public CardItem(int imageResource, String title, String price, long endDate) {
        this.imageResource = imageResource;
        this.title = title;
        this.price = price;
        this.endDate = endDate;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public long getEndDate() {
        return endDate;
    }
}

