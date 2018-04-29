package com.dekinci.lksbstu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.dekinci.lksbstu.fragment.scheduletypes.DailyScheduleFragment;
import com.dekinci.lksbstu.fragment.scheduletypes.MonthlyScheduleFragment;
import com.dekinci.lksbstu.fragment.scheduletypes.WeeklyScheduleFragment;
import com.example.hackaton.goprojectpisat.R;

public class ScheduleFragment extends Fragment {

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        Log.i("Schedule", "Fragment attached");

        RadioGroup group = view.findViewById(R.id.scheduleTypeRadioGroup);
        group.check(R.id.scheduleDaily);
        group.setOnCheckedChangeListener((group1, checkedId) -> {
            Fragment fragment = null;
            if (checkedId == R.id.scheduleDaily)
                fragment = new DailyScheduleFragment();
            else if (checkedId == R.id.scheduleWeekly)
                fragment = new WeeklyScheduleFragment();
            else if (checkedId == R.id.scheduleMonthly)
                fragment = new MonthlyScheduleFragment();

            if (fragment != null) {
                Activity activity = getActivity();
                if (activity != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.scheduleFragmentHolder, fragment);
                    fragmentTransaction.commit();
                }
                else
                    Log.e("ScheduleFragment", "activity is null!!!");
            }
        });
        return view;
    }
}
