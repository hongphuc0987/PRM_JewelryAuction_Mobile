package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;

public class AuctionModel extends BaseModel{

    private long id;
    private JewelryModel jewelry;
    private String startTime;
    private String endTime;
    private int step;
    private int totalBids;
    private String currentPrice;
    private UserModel winner;
    private String status;

    public AuctionModel(String createdAt, String updatedAt, long id, JewelryModel jewelry, String startTime, String endTime, int step, int totalBids, String currentPrice, UserModel winner, String status) {
        super(createdAt, updatedAt);
        this.id = id;
        this.jewelry = jewelry;
        this.startTime = startTime;
        this.endTime = endTime;
        this.step = step;
        this.totalBids = totalBids;
        this.currentPrice = currentPrice;
        this.winner = winner;
        this.status = status;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setJewelry(JewelryModel jewelry) {
        this.jewelry = jewelry;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setTotalBids(int totalBids) {
        this.totalBids = totalBids;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setWinner(UserModel winner) {
        this.winner = winner;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public long getId() {
        return id;
    }

    public JewelryModel getJewelry() {
        return jewelry;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getStep() {
        return step;
    }

    public int getTotalBids() {
        return totalBids;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }



    public UserModel getWinner() {
        return winner;
    }

    public String getStatus() {
        return status;
    }

}
