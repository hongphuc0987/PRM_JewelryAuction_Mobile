package com.prm.prm_jewelryauction_mobile.data.response;

import java.util.List;

public class BiddingHistoryResponse {
    private int code;
    private String message;
    private long timestamp;

    private List<BiddingHistory> data;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<BiddingHistory> getHistoryList() {
        return data;
    }
}
