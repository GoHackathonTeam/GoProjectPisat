package com.dekinci.lksbstu.communication.test;

import com.google.gson.annotations.SerializedName;

public class GradebookTest {

    private String lesson;

    private String type;

    private String data;

    private String teacher;

    private String result;

    public GradebookTest(String lesson, String type, String data, String teacher, String result) {
        this.lesson = lesson;
        this.type = type;
        this.data = data;
        this.teacher = teacher;
        this.result = result;
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
