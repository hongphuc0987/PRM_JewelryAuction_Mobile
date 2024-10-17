package com.prm.prm_jewelryauction_mobile.model;

public class UserModel {
    private int id;
    private String full_name;
    private String phoneNumber;
    private String email;
    private String address;
    private String imageUrl;
    private String dateOfBirth;
    private RoleModel roleId;
    private boolean emailVerified;
    private boolean isActive;

    public UserModel(int id, String full_name, String phoneNumber, String email, String address, String imageUrl, String dateOfBirth, RoleModel roleId, boolean emailVerified, boolean isActive) {
        this.id = id;
        this.full_name = full_name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.imageUrl = imageUrl;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
        this.emailVerified = emailVerified;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return full_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public RoleModel getRoleId() {
        return roleId;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public boolean isActive() {
        return isActive;
    }
}
