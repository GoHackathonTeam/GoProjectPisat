package com.dekinci.lksbstu.communication.server;


import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.MonthSchedule;
import com.dekinci.lksbstu.communication.structure.ScheduleItem;
import com.dekinci.lksbstu.communication.structure.WeekSchedule;

import java.util.ArrayList;

public class ScheduleServer {
    private static final String lessonType = "Практика";
    private static final String lesson = "Программирование";
    private static final String prepod = "Глухих М.В.";
    private static final String place = "ГЗ, ауд 237";

    private static boolean evenOdd = false;

    public DaySchedule getDaySchedule(String date) {
        ArrayList<ScheduleItem> arrayList = new ArrayList<>();
        for (int i = 8; i < 20; i += 2)
            arrayList.add(new ScheduleItem(lessonType, lesson, prepod, place, i + ":00"));

        return new DaySchedule(arrayList, date);
    }

    public WeekSchedule getWeekSchedule(String date) {
        ArrayList<DaySchedule> daySchedules = new ArrayList<>();
        int start = Integer.parseInt(date.trim().split("[ .]")[0]);

        for (int i = start; i < start + 7; i++)
            daySchedules.add(getDaySchedule(i + " мая 2018"));

        evenOdd = !evenOdd;
        return new WeekSchedule(evenOdd ? "четная" : "нечетная", daySchedules);
    }

    public MonthSchedule getMonthSchedule(String date) {
        ArrayList<DaySchedule> daySchedules = new ArrayList<>();

        for (int i = 1; i < 30; i++)
            daySchedules.add(getDaySchedule(i + " мая 2018"));

        return new MonthSchedule(daySchedules);
    }
}
