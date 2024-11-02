package com.prm.prm_jewelryauction_mobile.data.request.auction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAuctionRequest {
    private Long jewelryId;

    private String  startTime;

    private String endTime;

    private float step;

    public CreateAuctionRequest(Long jewelryId, String startTime, String endTime, float step) {
        this.jewelryId = jewelryId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.step = step;
    }

    public Long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(Long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}
