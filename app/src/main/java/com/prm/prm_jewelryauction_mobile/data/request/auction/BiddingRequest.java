package com.prm.prm_jewelryauction_mobile.data.request.auction;

public class BiddingRequest {
    private long auctionId;

    private float bidAmount;

    public BiddingRequest(long auctionId, float bidAmount) {
        this.auctionId = auctionId;
        this.bidAmount = bidAmount;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }
}
