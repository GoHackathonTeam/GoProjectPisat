package com.dekinci.lksbstu.communication.structure;

public class Schedule {
    String data;
    String lessonType;
    String lesson;
    String teacher;
    String place;

    public Schedule(String data, String lessonType, String lesson, String teacher, String place){
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
