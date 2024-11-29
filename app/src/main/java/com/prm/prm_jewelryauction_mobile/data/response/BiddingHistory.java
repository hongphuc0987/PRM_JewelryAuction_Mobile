package com.prm.prm_jewelryauction_mobile.data.response;

public class BiddingHistory {
    private Long id;
    private Long auctionId;
    private String userName;
    private String email;
    private float bidAmount;
    private String bidTime;
    private String status;

    public Long getId() {
        return id;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public String getBidTime() {
        return bidTime;
    }

    public String getStatus() {
        return status;
    }
}
