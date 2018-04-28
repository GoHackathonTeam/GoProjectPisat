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

    public Gradebook(String lesson, String type, String data, String teacher, String result) {
        this.lesson = lesson;
        this.type = type;
        this.data = data;
        this.teacher = teacher;
        this.result = result;
    }

    public Gradebook(){

    }

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
