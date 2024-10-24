package com.prm.prm_jewelryauction_mobile.model;

public class CheckoutRequest {
    private Long auction_id;
    private String full_name;
    private String phone_number;
    private String address;

    // Constructor
    public CheckoutRequest(Long auction_id, String full_name, String phone_number, String address) {
        this.auction_id = auction_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.address = address;
    }

    // Getters and setters
    public Long getAuctionId() {
        return auction_id;
    }

    public void setAuctionId(Long auction_id) {
        this.auction_id = auction_id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
