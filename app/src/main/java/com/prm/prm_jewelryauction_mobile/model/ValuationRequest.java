package com.prm.prm_jewelryauction_mobile.model;

public class ValuationRequest {
    private int jewelryId;
    private int desiredPrice;
    private String paymentMethod;
    private String notes;
    private String valuatingMethod;
    private String address;
    private boolean online;

    public ValuationRequest(int jewelryId, int desiredPrice, String paymentMethod, String notes, String valuatingMethod, String address, boolean online) {
        this.jewelryId = jewelryId;
        this.desiredPrice = desiredPrice;
        this.paymentMethod = paymentMethod;
        this.notes = notes;
        this.valuatingMethod = valuatingMethod;
        this.address = address;
        this.online = online;
    }

    public int getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(int jewelryId) {
        this.jewelryId = jewelryId;
    }

    public int getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(int desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getValuatingMethod() {
        return valuatingMethod;
    }

    public void setValuatingMethod(String valuatingMethod) {
        this.valuatingMethod = valuatingMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "ValuationRequest{" +
                "jewelryId=" + jewelryId +
                ", desiredPrice=" + desiredPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", notes='" + notes + '\'' +
                ", valuatingMethod='" + valuatingMethod + '\'' +
                ", address='" + address + '\'' +
                ", online=" + online +
                '}';
    }
}
