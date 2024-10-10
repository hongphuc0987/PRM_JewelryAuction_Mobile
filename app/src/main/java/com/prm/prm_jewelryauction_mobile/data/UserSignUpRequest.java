package com.prm.prm_jewelryauction_mobile.data;

public class UserSignUpRequest {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String dob;
    private String password;
    private String confirmPassword;

    public UserSignUpRequest(String fullName, String email, String phone, String address, String dob, String password, String confirmPassword) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getter và Setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}