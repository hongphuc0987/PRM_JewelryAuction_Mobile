package com.prm.prm_jewelryauction_mobile.data.request.auth;

public class UserSignInRequest {
    private String email;

    private String password;

    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
