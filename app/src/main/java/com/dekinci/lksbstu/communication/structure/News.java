package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class News {

    public News(String id, String header, String body, String date) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.date = date;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("header")
    private String header;

    @SerializedName("body")
    private String body;

    @SerializedName("date")
    private String date;

    public String getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }
}
