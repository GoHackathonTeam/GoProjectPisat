package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.dekinci.lksbstu.utils.Utils;
import com.example.hackaton.goprojectpisat.R;

import java.util.Calendar;
import java.util.Date;

public class MonthlyScheduleFragment extends Fragment implements ScheduleShower {
    private CalendarView calendarView;
    private Date currentDate;
    private Calendar calendar;

    private DayManager manager;

    @Override
    public void onDetach() {
        super.onDetach();
        manager = null;
    }

    @Override
    public void injectDayManager(DayManager manager) {
        this.manager = manager;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_schedule, container, false);

        calendar = Calendar.getInstance();
        currentDate = calendar.getTime();

        calendarView = view.findViewById(R.id.scheduleCalendar);
        calendarView.setDate(currentDate.getTime());
        calendarView.setOnDateChangeListener((v, year, month, dayOfMonth) ->
                manager.showDay(Utils.ymdToDate(year, month, dayOfMonth)));

        return view;
    }

    @Override
    public void next() {
    }

    @Override
    public void previous() {
    }

    @Override
    public void show(Date date) {

    }
}
