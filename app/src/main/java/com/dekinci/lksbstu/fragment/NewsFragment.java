package com.dekinci.lksbstu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.model.PolyManager;
import com.example.hackaton.goprojectpisat.R;

public class NewsFragment extends Fragment {

    public NewsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("News", "Fragment attached");
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        LinearLayout holder = view.findViewById(R.id.news_holder);
        PolyManager.get().getApi().getNews(newsList -> {
            for (News news : newsList) {
                TextView date = holder.findViewById(R.id.news_time);
                TextView header = holder.findViewById(R.id.news_header);
                TextView body = holder.findViewById(R.id.news_body);

                getActivity().runOnUiThread(() -> {
                    date.setText(news.getDate());
                    header.setText(news.getHeader());
                    String bodyText = news.getBody();
                    if (bodyText.length() > 200)
                        bodyText = bodyText.substring(0, 197) + "...";
                    body.setText(bodyText);
                });
            }
        }, 0, Integer.MAX_VALUE);
        return view;
    }
}
