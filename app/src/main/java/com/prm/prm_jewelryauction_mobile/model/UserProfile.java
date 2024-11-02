package com.prm.prm_jewelryauction_mobile.model;

public class UserProfile {
    private int id;
    private String full_name;
    private String phone_number;
    private String email;
    private String address;
    private String imageUrl;
    private String date_of_birth;
    private RoleModel role_id;
    private boolean email_verified;
    private boolean is_active;

    public UserProfile(int id, String full_name, String phone_number, String email, String address, String imageUrl, String date_of_birth, RoleModel role_id, boolean email_verified, boolean is_active) {
        this.id = id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.imageUrl = imageUrl;
        this.date_of_birth = date_of_birth;
        this.role_id = role_id;
        this.email_verified = email_verified;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public RoleModel getRole_id() {
        return role_id;
    }

    public void setRole_id(RoleModel role_id) {
        this.role_id = role_id;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", role_id=" + role_id +
                ", email_verified=" + email_verified +
                ", is_active=" + is_active +
                '}';
    }
}
