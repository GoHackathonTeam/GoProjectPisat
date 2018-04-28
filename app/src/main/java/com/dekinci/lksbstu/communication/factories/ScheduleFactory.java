package com.dekinci.lksbstu.communication.factories;

import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.MonthSchedule;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.WeekSchedule;

import java.util.ArrayList;

public class ScheduleFactory {
    private static final String lessonType = "Практика";
    private static final String lesson = "Программирование";
    private static final String prepod = "Глухих М.В.";
    private static final String place = "ГЗ, ауд 237";

    private static boolean evenOdd = false;

    public DaySchedule createDaySchedule() {
        ArrayList<Schedule> arrayList = new ArrayList<>();

        for (int i = 8; i < 20; i += 2)
            arrayList.add(new Schedule(lessonType, lesson, prepod, place, i + ":00"));

        return new DaySchedule(arrayList, "11 сентября 2001");
    }

    public WeekSchedule createWeekSchedule() {
        ArrayList<DaySchedule> daySchedules = new ArrayList<>();

        for (int i = 0; i < 6; i++)
            daySchedules.add(createDaySchedule());

        evenOdd = !evenOdd;
        return new WeekSchedule(evenOdd ? "четная" : "нечетная", daySchedules);
    }

    public MonthSchedule createMonthSchedule() {
        ArrayList<DaySchedule> daySchedules = new ArrayList<>();

        for (int i = 0; i < 6; i++)
            daySchedules.add(createDaySchedule());

        return new MonthSchedule(daySchedules);
    }
}
