package com.prm.prm_jewelryauction_mobile.model;

public class ProfileModel {
    private UserProfile user;
    private double money;

    public ProfileModel(UserProfile user, double money) {
        this.user = user;
        this.money = money;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "user=" + user.toString() +
                ", money=" + money +
                '}';
    }
}
