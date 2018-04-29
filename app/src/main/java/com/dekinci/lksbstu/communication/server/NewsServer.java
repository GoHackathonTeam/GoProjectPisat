package com.dekinci.lksbstu.communication.server;

import com.dekinci.lksbstu.communication.structure.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.dekinci.lksbstu.utils.Utils.randomFromArr;

public class NewsServer {
    private static final AtomicInteger ids = new AtomicInteger(0);
    private static final Map<String, News> news = new HashMap<>();

    private Random r = new Random();
    private static String[] headers = {"Кое-что случилось", "Внимание!!!", "Новости"};
    private static String[] bodies = {
            "Вы и сами наверное догадались, что <b>новости</b> это <i>новости</i>",
            "Очень важная информация",
            "Lorem ipsum lorem ipsum lorem ipsum blah blah blah"};


    public List<News> getNewsByDate(String date) {
        List<News> result = getForDate(date);
        if (result.isEmpty()) {
            for (int i = 0; i < 3; i++)
                result.add(createNews(date));
        }

        return result;
    }

    private News createNews(String date) {
        String id = String.valueOf(ids.incrementAndGet());
        News n = new News(id, randomFromArr(headers), randomFromArr(bodies), date);
        news.put(id, n);
        return n;
    }

    public News getNewsById(String id) {
        return news.get(id);
    }

    public News forceNewsById(String id) {
        News n = getNewsById(id);
        if (n == null)
            n = createNews(r.nextInt(20) + 1 + ".05.2018");
        return n;
    }

    private List<News> getForDate(String date) {
        List<News> list = new ArrayList<>();
        for (Map.Entry<String, News> entry : news.entrySet())
            if (entry.getValue().getDate().equals(date))
                list.add(entry.getValue());
        return list;
    }
}
