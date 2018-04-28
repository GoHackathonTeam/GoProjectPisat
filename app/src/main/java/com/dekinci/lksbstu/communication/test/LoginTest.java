package com.dekinci.lksbstu.communication.test;

import com.google.gson.annotations.SerializedName;

public class LoginTest {

    private String TOKEN;

    private String ID;

    public LoginTest(String TOKEN, String ID) {
        this.TOKEN = TOKEN;
        this.ID = ID;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public String getID() {
        return ID;
    }
}
