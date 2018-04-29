package com.dekinci.lksbstu.communication.structure;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {
    private List<ScheduleItem> daySchedule = new ArrayList<>();
    private String date;

    public DaySchedule(List<ScheduleItem> scheduleItems, String date) {
        this.date = date;
        daySchedule = scheduleItems;
    }

    public DaySchedule() {
    }

    public void add(ScheduleItem scheduleItem){
        daySchedule.add(scheduleItem);
    }

    public List<ScheduleItem> getDaySchedule() {
        return daySchedule;
    }

    public String getDate() {
        return date;
    }
}
