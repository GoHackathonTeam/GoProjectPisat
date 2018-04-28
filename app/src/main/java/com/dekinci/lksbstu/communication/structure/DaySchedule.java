package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {

    @SerializedName("daySched")
    private List<Schedule> daySched = new ArrayList<>();

    @SerializedName("data")
    private String data;

    public DaySchedule(String data) {
        this.data = data;
    }

    public DaySchedule() {
        this.data = data;
    }

    public void add(Schedule schedule){
        daySched.add(schedule);
    }

}
