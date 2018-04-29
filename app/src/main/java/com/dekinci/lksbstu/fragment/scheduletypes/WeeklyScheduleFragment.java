package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.ScheduleItem;
import com.dekinci.lksbstu.fragment.ScheduleFragment;
import com.dekinci.lksbstu.model.PolyManager;
import com.example.hackaton.goprojectpisat.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeeklyScheduleFragment extends Fragment implements ScheduleShower {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    private TextView dateText;
    private Calendar calendar;

    private LinearLayout mainHolder;
    private LayoutInflater inflater;
    private Date currentDate;

    private DayManager manager;

    public WeeklyScheduleFragment() {
    }

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
        calendar = Calendar.getInstance();
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_weekly_schedule, container, false);
        mainHolder = view.findViewById(R.id.schedule_holder);

        currentDate = calendar.getTime();
        inflateSchedule();

        return view;
    }

    @Override
    public void next() {
        currentDate = new Date(currentDate.getTime() + 7 * 24 * 60 * 60 * 1000);
        inflateSchedule();
    }

    @Override
    public void previous() {
        currentDate = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000);
        inflateSchedule();
    }

    @Override
    public void show(Date date) {
        currentDate = date;
        inflateSchedule();
    }

    public void inflateSchedule() {
        String date = formatter.format(calendar.getTime());

        PolyManager.get().getApi().getSchedule(date, "week", (scheduleList) -> {
            for (DaySchedule daySchedule : scheduleList) {
                View dayScheduleView = inflater.inflate(R.layout.day_schedule_layout, mainHolder, false);
                TextView scheduleDayName = dayScheduleView.findViewById(R.id.schedule_day_name);
                scheduleDayName.setText(date);
                scheduleDayName.setOnClickListener((v) -> {
                    manager.showDay(currentDate);
                });

                LinearLayout classesHolder = dayScheduleView.findViewById(R.id.schedule_classes_holder);
                for (ScheduleItem scheduleItem : daySchedule.getDaySchedule()) {
                    View classView = inflater.inflate(R.layout.class_schedule_layout, classesHolder, false);
                    TextView time = classView.findViewById(R.id.class_time);
                    TextView name = classView.findViewById(R.id.class_name);
                    TextView teacher = classView.findViewById(R.id.class_teacher);
                    TextView room = classView.findViewById(R.id.class_room);
                    time.setText(scheduleItem.getTime());
                    name.setText(String.format("%s (%s)", scheduleItem.getLesson(), scheduleItem.getLessonType()));
                    teacher.setText(scheduleItem.getTeacher());
                    room.setText(scheduleItem.getPlace());
                    classesHolder.addView(classView);
                }
                mainHolder.addView(dayScheduleView);
            }
        });
    }
}
