package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("senderId")
    private String senderId;

    @SerializedName("receiverId")
    private String receiverId;

    @SerializedName("text")
    private String text;

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }
}
