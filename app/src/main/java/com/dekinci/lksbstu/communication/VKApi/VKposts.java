package com.dekinci.lksbstu.communication.VKApi;

import com.google.gson.annotations.SerializedName;

public class VKposts {
    @SerializedName("text")
    private String text;

    @SerializedName("photo_604")
    private String image;

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}
