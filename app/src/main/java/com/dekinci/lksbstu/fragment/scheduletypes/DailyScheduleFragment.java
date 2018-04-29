package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.structure.ScheduleItem;
import com.dekinci.lksbstu.model.PolyManager;
import com.example.hackaton.goprojectpisat.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


public class DailyScheduleFragment extends Fragment implements ScheduleShower {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    private TextView dateText;
    private Calendar calendar;
    private LinearLayout schedules;
    private LayoutInflater inflater;
    private Date currentDate;

    public DailyScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_daily_schedule, container, false);
        schedules = view.findViewById(R.id.schedule_classes_holder);
        calendar = Calendar.getInstance();
        dateText = view.findViewById(R.id.schedule_day_name);

        currentDate = calendar.getTime();
        inflateSchedule();

        return view;
    }

    @Override
    public void next() {
        currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        inflateSchedule();
    }

    @Override
    public void previous() {
        currentDate = new Date(currentDate.getTime() - 24 * 60 * 60 * 1000);
        inflateSchedule();
    }

    @Override
    public void show(Date date) {
        currentDate = date;
        inflateSchedule();
    }

    public void inflateSchedule() {
        String textDate = formatter.format(currentDate);

        PolyManager.get().getApi().getSchedule(textDate, "day", (scheduleList) -> {
            Log.w("Daily scgedule", "Schedule received");
            dateText.setText(textDate);

            if (scheduleList.isEmpty()) {
                TextView nothingText = new TextView(getContext());
                nothingText.setText(R.string.no_classes);
                ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                nothingText.setLayoutParams(params);
                schedules.addView(nothingText);
            }

            Collection<ScheduleItem> scheduleItemCollection = scheduleList.get(0).getDaySchedule();
            for (ScheduleItem scheduleItem : scheduleItemCollection) {
                View element = inflater.inflate(R.layout.class_schedule_layout, schedules, false);
                TextView time = element.findViewById(R.id.class_time);
                time.setText(scheduleItem.getTime());

                TextView name = element.findViewById(R.id.class_name);
                name.setText(String.format("%s (%s)", scheduleItem.getLesson(), scheduleItem.getLessonType()));

                TextView teacher = element.findViewById(R.id.class_teacher);
                teacher.setText(scheduleItem.getTeacher());

                TextView room = element.findViewById(R.id.class_room);
                room.setText(scheduleItem.getPlace());

                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                element.setLayoutParams(params);
                schedules.addView(element);
            }
        });
    }
}
