package com.dekinci.lksbstu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.dekinci.lksbstu.fragment.scheduletypes.DailyScheduleFragment;
import com.dekinci.lksbstu.fragment.scheduletypes.MonthlyScheduleFragment;
import com.dekinci.lksbstu.fragment.scheduletypes.ScheduleShower;
import com.dekinci.lksbstu.fragment.scheduletypes.WeeklyScheduleFragment;
import com.dekinci.lksbstu.utils.OnSwipeTouchListener;
import com.example.hackaton.goprojectpisat.R;

public class ScheduleFragment extends Fragment {
    private ScheduleShower scheduleShower;

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

        initFragment(new DailyScheduleFragment());

        group.setOnCheckedChangeListener((group1, checkedId) -> {
            Fragment fragment = null;
            if (checkedId == R.id.scheduleDaily) {
                DailyScheduleFragment f = new DailyScheduleFragment();
                fragment = f;
                scheduleShower = f;
            } else if (checkedId == R.id.scheduleWeekly) {
                WeeklyScheduleFragment f = new WeeklyScheduleFragment();
                fragment = f;
                scheduleShower = f;
            } else if (checkedId == R.id.scheduleMonthly) {
                MonthlyScheduleFragment f = new MonthlyScheduleFragment();
                fragment = f;
                scheduleShower = f;
            }

            if (fragment != null) {
                replaceBy(fragment);
            }
        });

        addGestureListener(view.findViewById(R.id.scheduleFragmentHolder));
        return view;
    }

    private void initFragment(Fragment fragment) {
        scheduleShower = (ScheduleShower) fragment;

        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.scheduleFragmentHolder, fragment);
            fragmentTransaction.commit();
        }
        else
            Log.e("ScheduleFragment", "activity is null!!!");
    }

    private void replaceBy(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.scheduleFragmentHolder, fragment);
            fragmentTransaction.commit();
        }
        else
            Log.e("ScheduleFragment", "activity is null!!!");
    }

    private void addGestureListener(View view) {
        view.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeLeft() {
                scheduleShower.next();
            }

            @Override
            public void onSwipeRight() {
                scheduleShower.previous();
            }
        });
    }
}
