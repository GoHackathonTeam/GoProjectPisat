package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Login {
    @SerializedName("access_token")
    private String token;

    @SerializedName("id")
    private String id;

    public static Login fromString(String str){
        List<String> arr = Arrays.asList(str.split("&&&"));
        return new Login(arr.get(0), arr.get(1));
    }

    public Login(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public Login(){
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    @Override
    public  String toString(){
        return token + "&&&" + id;
    }
}
