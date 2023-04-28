package com.youth4work.HSSC_2023.network.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName( "username")
    private String username;

    @SerializedName( "token")
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
