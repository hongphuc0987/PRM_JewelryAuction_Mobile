package com.prm.prm_jewelryauction_mobile.data.response;

import com.prm.prm_jewelryauction_mobile.model.AuctionModel;
import com.prm.prm_jewelryauction_mobile.model.UserModel;

public class Wishlist {
    private long id;
    private UserModel userId;
    private AuctionModel auction;

    public long getId() {
        return id;
    }

    public UserModel getUserId() {
        return userId;
    }

    public AuctionModel getAuction() {
        return auction;
    }
}
