package com.dekinci.lksbstu.communication.structure;

public class Announcement {
    private String from;

    private String to;

    private String header;
    private String body;

    private String date;

    public Announcement(String from, String to, String header, String body, String date) {
        this.from = from;
        this.to = to;
        this.header = header;
        this.body = body;
        this.date = date;
    }

    public Announcement() {
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
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
