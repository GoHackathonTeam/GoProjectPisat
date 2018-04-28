package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.goprojectpisat.R;

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
        return inflater.inflate(R.layout.fragment_weekly_schedule, container, false);
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }
}
