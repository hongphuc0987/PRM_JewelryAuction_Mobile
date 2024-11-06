package com.prm.prm_jewelryauction_mobile.data.response;

import com.prm.prm_jewelryauction_mobile.model.AuctionModel;

import java.util.List;

public class MyAuctionResponse {

    private int code;
    private String message;
    private long timestamp;
    private List<AuctionModel> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<AuctionModel> getData() {
        return data;
    }
}
