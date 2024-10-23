package com.prm.prm_jewelryauction_mobile.model;

import java.util.List;

public class ApiResponseWinAuction {
    private int code;
    private String message;
    private long timestamp;
    private List<AuctionItem> data;

    // Getters
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<AuctionItem> getData() {
        return data;
    }
}
