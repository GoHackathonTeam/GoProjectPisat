package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Login {
    @SerializedName("access_token")
    private String TOKEN;

    @SerializedName("id")
    private String ID;

    public String getTOKEN() {
        return TOKEN;
    }

    public String getID() {
        return ID;
    }

    public Login(String TOKEN, String ID) {
        this.TOKEN = TOKEN;
        this.ID = ID;
    }

    public Login(){

    }

    @Override
    public  String toString(){
        return TOKEN + "&&&" + ID;
    }

    public static Login fromString(String str){
        List<String> arr = Arrays.asList(str.split("&&&"));
        return new Login(arr.get(0), arr.get(1));
    }
}
