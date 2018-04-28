package com.dekinci.lksbstu.communication.structure.pojos;

import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("id")
    private String id;

    @SerializedName("number")
    private String number;

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
