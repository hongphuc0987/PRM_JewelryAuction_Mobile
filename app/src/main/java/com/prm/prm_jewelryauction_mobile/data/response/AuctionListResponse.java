package com.prm.prm_jewelryauction_mobile.data.response;

import java.time.LocalDate;

public class AuctionListResponse {
    private String jewelryName;

    private Long currentPrice;

    private String status;

    private LocalDate endTime;

    private String jewelryImages;

    public AuctionListResponse(String jewelryName, Long currentPrice, String status, LocalDate endTime, String jewelryImages) {
        this.jewelryName = jewelryName;
        this.currentPrice = currentPrice;
        this.status = status;
        this.endTime = endTime;
        this.jewelryImages = jewelryImages;
    }

    public String getJewelryName() {
        return jewelryName;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getJewelryImages() {
        return jewelryImages;
    }

    public void setJewelryName(String jewelryName) {
        this.jewelryName = jewelryName;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public void setJewelryImages(String jewelryImages) {
        this.jewelryImages = jewelryImages;
    }
}
