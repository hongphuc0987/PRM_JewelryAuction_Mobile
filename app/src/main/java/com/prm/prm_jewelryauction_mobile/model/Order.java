package com.prm.prm_jewelryauction_mobile.model;
public class Order {
    private String jewelryName;
    private double winningPrice;
    private String auctionEndDate;
    private String winnerName;
    private String winnerPhoneNumber;

    // Constructor
    public Order(String jewelryName, double winningPrice, String auctionEndDate, String winnerName, String winnerPhoneNumber) {
        this.jewelryName = jewelryName;
        this.winningPrice = winningPrice;
        this.auctionEndDate = auctionEndDate;
        this.winnerName = winnerName;
        this.winnerPhoneNumber = winnerPhoneNumber;
    }

    // Getters
    public String getJewelryName() {
        return jewelryName;
    }

    public double getWinningPrice() {
        return winningPrice;
    }

    public String getAuctionEndDate() {
        return auctionEndDate;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getWinnerPhoneNumber() {
        return winnerPhoneNumber;
    }
}
