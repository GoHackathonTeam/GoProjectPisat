package com.dekinci.lksbstu.communication.test;

import com.google.gson.annotations.SerializedName;

public class ScheduleTest {
    private String data;

    private String lessonType;

    private String lesson;

    private String teacher;

    private String place;

    public ScheduleTest(String data, String lessonType, String lesson, String teacher, String place) {
        this.data = data;
        this.lessonType = lessonType;
        this.lesson = lesson;
        this.teacher = teacher;
        this.place = place;
    }

    public String getData(){
        return data;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getLesson() {
        return lesson;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getPlace() {
        return place;
    }
}
