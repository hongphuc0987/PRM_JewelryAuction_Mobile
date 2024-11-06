package com.prm.prm_jewelryauction_mobile.data.response;

import java.util.List;

public class WishListRespone {

    private int code;
    private String message;
    private long timestamp;
    private List<Wishlist> data;

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

    public List<Wishlist> getData() {
        return data;
    }

    public void setData(List<Wishlist> data) {
        this.data = data;
    }
}
