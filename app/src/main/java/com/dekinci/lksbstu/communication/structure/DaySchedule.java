package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {

    @SerializedName("daySched")
    private List<Schedule> daySched = new ArrayList<>();

    @SerializedName("date")
    private String date;

    public DaySchedule(List<Schedule> schedules, String date) {
        this.date = date;
        daySched = schedules;
    }

    public DaySchedule() {
    }

    public void add(Schedule schedule){
        daySched.add(schedule);
    }

    public List<Schedule> getDaySched() {
        return daySched;
    }

    public String getDate() {
        return date;
    }
}
