package com.dekinci.lksbstu.communication.structure;

import java.util.List;

public class WeekSchedule {
    private String type;
    private List<DaySchedule> daySchedules;

    public WeekSchedule(String type, List<DaySchedule> daySchedules) {
        this.type = type;
        this.daySchedules = daySchedules;
    }

    public WeekSchedule() {
    }

    public String getType() {
        return type;
    }

    public List<DaySchedule> getDaySchedules() {
        return daySchedules;
    }
}
