package com.dekinci.lksbstu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.goprojectpisat.R;

public class DocsFragment extends Fragment {
    public DocsFragment() {
        // Required empty public constructor
    }

    public static DocsFragment newInstance() {
        DocsFragment fragment = new DocsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Docs", "Fragment attached");
        return inflater.inflate(R.layout.fragment_docs, container, false);
    }
}
