package com.prm.prm_jewelryauction_mobile.model;

import org.json.JSONObject;

import okhttp3.ResponseBody;

public class AuctionItem {
    private long id;
    private String name;
    private String description;
    private String thumbnail;
    private double price;
    private String startTime;
    private String endTime;
    private double currentPrice;
    private String status;
    private String winnerName;

    // Constructor, getters and setters
    public AuctionItem(long id, String name, String description, String thumbnail,
                       double price, String startTime, String endTime,
                       double currentPrice, String status, String winnerName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentPrice = currentPrice;
        this.status = status;
        this.winnerName = winnerName;
    }

    // Getters and Setters...

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    private AuctionItem parseResponseBody(ResponseBody responseBody) {
        try {
            // Convert ResponseBody to JSON string
            String jsonString = responseBody.string();
            JSONObject jsonObject = new JSONObject(jsonString);

            // Extract necessary fields
            long id = jsonObject.getLong("id");
            String name = jsonObject.getJSONObject("jewelry").getString("name");
            String description = jsonObject.getJSONObject("jewelry").getString("description");
            String thumbnail = jsonObject.getJSONObject("jewelry").getString("thumbnail");
            double price = jsonObject.getJSONObject("jewelry").getDouble("price");
            String startTime = jsonObject.getString("startTime");
            String endTime = jsonObject.getString("endTime");
            double currentPrice = jsonObject.getDouble("currentPrice");
            String status = jsonObject.getString("status");
            String winnerName = jsonObject.optJSONObject("winner") != null
                    ? jsonObject.getJSONObject("winner").getString("full_name")
                    : "No winner";

            return new AuctionItem(id, name, description, thumbnail, price,
                    startTime, endTime, currentPrice, status, winnerName);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle parsing error
        }
    }
}
