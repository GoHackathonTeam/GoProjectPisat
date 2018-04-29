package com.dekinci.lksbstu.communication.structure;

public class ScheduleItem {
    private String time;
    private String lessonType;
    private String lesson;
    private String teacher;
    private String place;

    public ScheduleItem(String lessonType, String lesson, String teacher, String place, String dateTime) {
        this.lessonType = lessonType;
        this.lesson = lesson;
        this.teacher = teacher;
        this.place = place;
        this.time = dateTime;
    }

    public ScheduleItem(){}

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

    public String getTime() {
        return time;
    }
}
