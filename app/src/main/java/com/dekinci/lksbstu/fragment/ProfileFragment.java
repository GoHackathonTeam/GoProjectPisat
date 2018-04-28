package com.dekinci.lksbstu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dekinci.lksbstu.PolyManager;
import com.example.hackaton.goprojectpisat.R;

public class ProfileFragment extends Fragment {
    private LogOutListener logOutListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View resultView = inflater.inflate(R.layout.fragment_profile, container, false);
        resultView.findViewById(R.id.button_logout).setOnClickListener(v -> logOutListener.logout());
        return resultView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LogOutListener) {
            logOutListener = (LogOutListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement LogOutListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logOutListener = null;
    }

    public interface LogOutListener {
        void logout();
    }
}
