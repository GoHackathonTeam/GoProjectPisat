package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {

    @SerializedName("daySched")
    private ArrayList<Schedule> daySched;

    @SerializedName("data")
    private String data;

    public void add(Schedule schedule){
        daySched.add(schedule);
    }

}
