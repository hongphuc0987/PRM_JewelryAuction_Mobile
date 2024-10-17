package com.prm.prm_jewelryauction_mobile.model;

import com.google.gson.annotations.SerializedName;

public class AuctionList {
    @SerializedName("data")
    private AuctionModel data;

    public AuctionList(AuctionModel data) {
        this.data = data;
    }

    public void setData(AuctionModel data) {
        this.data = data;
    }

    public AuctionModel getData() {
        return data;
    }
}
