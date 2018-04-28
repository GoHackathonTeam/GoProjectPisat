package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

public class News {

    public News(String id, String header, String body) {
        this.id = id;
        this.header = header;
        this.body = body;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("header")
    private String header;

    @SerializedName("body")
    private String body;

    public String getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }
}
