package com.dekinci.lksbstu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.utils.ResultCallback;
import com.example.hackaton.goprojectpisat.R;

import java.util.List;

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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("News", "Fragment attached");
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        LinearLayout holder = view.findViewById(R.id.Snews_holder);
        PolyManager.get().getApi().getNews(new ResultCallback<List<News>>() {

            @Override
            public void success(List<News> newsList) {
                for (News news : newsList) {
                    Log.d("News", news.getHeader());
                    View record = inflater.inflate(R.layout.news_layout, holder, false);
                    TextView date = record.findViewById(R.id.news_time);
                    TextView header = record.findViewById(R.id.news_header);
                    TextView body = record.findViewById(R.id.news_body);

                    String bodyText = news.getBody();
                    if (bodyText.length() > 200)
                        bodyText = bodyText.substring(0, 197) + "...";
                    date.setText(news.getHeader());
                    header.setText(Html.fromHtml(bodyText, Html.FROM_HTML_MODE_COMPACT));
                    body.setText(news.getDate());

                    holder.addView(record);
                }
            }

            @Override
            public void failure(Throwable e) {
                e.printStackTrace();
            }
        }, 0, Integer.MAX_VALUE);

        return view;
    }
}
