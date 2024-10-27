package com.prm.prm_jewelryauction_mobile.model;

public class Valuation {
    private int id;
    private UserModel staff;
    private JewelryModel jewelry;
    private double valuation_value;
    private String notes;
    private String status;
    private double desiredPrice;
    private double startingPrice;
    private String paymentMethod;
    private double valuatingFee;
    private String valuatingMethod;
    private String address;
    private boolean online;

    public Valuation(int id, UserModel staff, JewelryModel jewelry, double valuation_value, String notes, String status, double desiredPrice, double startingPrice, String paymentMethod, double valuatingFee, String valuatingMethod, String address, boolean online) {
        this.id = id;
        this.staff = staff;
        this.jewelry = jewelry;
        this.valuation_value = valuation_value;
        this.notes = notes;
        this.status = status;
        this.desiredPrice = desiredPrice;
        this.startingPrice = startingPrice;
        this.paymentMethod = paymentMethod;
        this.valuatingFee = valuatingFee;
        this.valuatingMethod = valuatingMethod;
        this.address = address;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getStaff() {
        return staff;
    }

    public void setStaff(UserModel staff) {
        this.staff = staff;
    }

    public JewelryModel getJewelry() {
        return jewelry;
    }

    public void setJewelry(JewelryModel jewelry) {
        this.jewelry = jewelry;
    }

    public double getValuation_value() {
        return valuation_value;
    }

    public void setValuation_value(double valuation_value) {
        this.valuation_value = valuation_value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(double desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getValuatingFee() {
        return valuatingFee;
    }

    public void setValuatingFee(double valuatingFee) {
        this.valuatingFee = valuatingFee;
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
}
