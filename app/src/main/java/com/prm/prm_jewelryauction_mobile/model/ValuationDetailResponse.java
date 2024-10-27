package com.prm.prm_jewelryauction_mobile.model;

import java.util.List;

public class ValuationDetailResponse {
    private int code;
    private String message;
    private long timestamp;
    private Valuation data;

    public ValuationDetailResponse(int code, String message, long timestamp, Valuation data) {
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

    public Valuation getData() {
        return data;
    }

    public void setData(Valuation data) {
        this.data = data;
    }
}
