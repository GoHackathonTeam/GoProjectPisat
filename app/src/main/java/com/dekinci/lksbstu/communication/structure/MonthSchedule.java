package com.dekinci.lksbstu.communication.structure;

import java.util.List;

public class MonthSchedule {
    private List<DaySchedule> schedules;

    public MonthSchedule(List<DaySchedule> schedules) {
        this.schedules = schedules;
    }

    public MonthSchedule() {
    }

    public List<DaySchedule> getSchedules() {
        return schedules;
    }
}
