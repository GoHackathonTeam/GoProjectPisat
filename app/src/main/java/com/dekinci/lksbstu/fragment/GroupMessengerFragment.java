package com.dekinci.lksbstu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.goprojectpisat.R;

public class GroupMessengerFragment extends Fragment {
    public GroupMessengerFragment() {
        // Required empty public constructor
    }

    public static GroupMessengerFragment newInstance(String param1, String param2) {
        GroupMessengerFragment fragment = new GroupMessengerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_messendger, container, false);
    }

}
