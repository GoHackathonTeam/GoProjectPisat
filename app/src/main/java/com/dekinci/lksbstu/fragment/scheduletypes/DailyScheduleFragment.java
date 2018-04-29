package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


public class DailyScheduleFragment extends Fragment implements ScheduleShower {


    public DailyScheduleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DailyScheduleFragment newInstance() {
        DailyScheduleFragment fragment = new DailyScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(calendar.getTime());
        View view = inflater.inflate(R.layout.fragment_daily_schedule, container, false);
        LinearLayout schedules = view.findViewById(R.id.schedule_classes_holder);
        PolyManager.get().getApi().getSchedule(date, "day", (scheduleList) -> {
            TextView dateText = view.findViewById(R.id.schedule_day_name);
            dateText.setText(date);

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
                time.setText("null");
                TextView name = element.findViewById(R.id.class_name);
                name.setText(scheduleItem.getLesson() + " (" + scheduleItem.getLessonType() + ")");
                TextView teacher = element.findViewById(R.id.class_teacher);
                teacher.setText(scheduleItem.getTeacher());
                TextView room = element.findViewById(R.id.class_room);
                room.setText(scheduleItem.getPlace());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                element.setLayoutParams(params);
                schedules.addView(element);
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
