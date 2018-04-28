package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

public class Gradebook {

    @SerializedName("lesson")
    private String lesson;

    @SerializedName("type")
    private String type;

    @SerializedName("data")
    private String data;

    @SerializedName("teacher")
    private String teacher;

    @SerializedName("result")
    private String result;


    public String getLesson() {
        return lesson;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getResult() {
        return result;
    }
}
