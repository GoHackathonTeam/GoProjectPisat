package com.dekinci.lksbstu.fragment.scheduletypes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.goprojectpisat.R;


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
        return inflater.inflate(R.layout.fragment_daily_schedule, container, false);
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }
}
