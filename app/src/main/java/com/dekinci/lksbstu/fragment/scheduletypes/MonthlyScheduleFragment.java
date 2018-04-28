package com.dekinci.lksbstu.fragment.scheduletypes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.goprojectpisat.R;

public class MonthlyScheduleFragment extends Fragment implements ScheduleShower {

    public MonthlyScheduleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MonthlyScheduleFragment newInstance() {
        MonthlyScheduleFragment fragment = new MonthlyScheduleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_schedule, container, false);
    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }
}
