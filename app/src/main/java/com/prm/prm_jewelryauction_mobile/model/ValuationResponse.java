package com.prm.prm_jewelryauction_mobile.model;

import java.util.List;

public class ValuationResponse {
    private int code;
    private String message;
    private long timestamp;
    private List<Valuation> data;

    public ValuationResponse(int code, String message, long timestamp, List<Valuation> data) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Valuation> getData() {
        return data;
    }

    public void setData(List<Valuation> data) {
        this.data = data;
    }
}
