package com.prm.prm_jewelryauction_mobile.model;

import java.util.List;

public class ProfileResponse {
    private int code;
    private String message;
    private long timestamp;
    private ProfileModel data;

    public ProfileResponse(int code, String message, long timestamp, ProfileModel data) {
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

    public ProfileModel getData() {
        return data;
    }

    public void setData(ProfileModel data) {
        this.data = data;
    }
}
