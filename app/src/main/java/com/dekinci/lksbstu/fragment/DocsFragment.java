package com.dekinci.lksbstu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_docs, container, false);
    }
}
