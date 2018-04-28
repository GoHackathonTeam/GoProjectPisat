package com.dekinci.lksbstu.communication.structure;

public class Gradebook {
    String lesson;
    String type;
    String data;
    String teacher;
    String result;

    public Gradebook(String lesson, String type, String data, String teacher, String result) {
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
