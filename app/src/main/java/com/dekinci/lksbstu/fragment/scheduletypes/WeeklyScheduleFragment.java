package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.PolyManager;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.example.hackaton.goprojectpisat.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

public class WeeklyScheduleFragment extends Fragment implements ScheduleShower {
    public WeeklyScheduleFragment() {
        // Required empty public constructor
    }

    public static WeeklyScheduleFragment newInstance() {
        WeeklyScheduleFragment fragment = new WeeklyScheduleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_weekly_schedule, container, false);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        final String date = formatter.format(calendar.getTime());
        View view = inflater.inflate(R.layout.fragment_daily_schedule, container, false);
        LinearLayout mainHolder = view.findViewById(R.id.schedule_holder);
        PolyManager.get().getApi().getSchedule(date, "day", (scheduleList) -> {
            for (DaySchedule daySchedule : scheduleList) {
                View dayScheduleView = inflater.inflate(R.layout.day_schedule_layout, mainHolder, false);
                TextView scheduleDayName = dayScheduleView.findViewById(R.id.schedule_day_name);
                scheduleDayName.setText(date);
                LinearLayout classesHolder = dayScheduleView.findViewById(R.id.schedule_classes_holder);
                for (Schedule schedule : daySchedule.getDaySched()) {
                    View classView = inflater.inflate(R.layout.class_schedule_layout, classesHolder, false);
                    TextView time = classView.findViewById(R.id.class_time);
                    TextView name = classView.findViewById(R.id.class_name);
                    TextView teacher = classView.findViewById(R.id.class_teacher);
                    TextView room = classView.findViewById(R.id.class_room);
                    time.setText(schedule.getTime());
                    name.setText(schedule.getLesson() + " (" + schedule.getLessonType() + ')');
                    teacher.setText(schedule.getTeacher());
                    room.setText(schedule.getPlace());
                    classesHolder.addView(classView);
                }
                mainHolder.addView(dayScheduleView);
            }
        });
        return view;
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }
}
