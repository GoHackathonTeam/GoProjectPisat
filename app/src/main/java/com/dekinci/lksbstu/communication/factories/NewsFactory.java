package com.dekinci.lksbstu.communication.factories;

import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.utils.Utils;

import java.util.concurrent.atomic.AtomicInteger;

import static com.dekinci.lksbstu.utils.Utils.randomFromArr;

public class NewsFactory {
    private static final AtomicInteger ids = new AtomicInteger(0);

    private static String[] headers = {"Кое-что случилось", "Внимание!!!", "Новости"};
    private static String[] bodies = {
            "Вы и сами наверное догадались, что <b>новости</b> это <i>новости</i>",
            "Очень важная информация",
            "Lorem ipsum lorem ipsum lorem ipsum blah blah blah"};
    private static String[] dates = {"2017.12.01 12:01"};


    public News generateNew() {
        return new News(String.valueOf(ids.incrementAndGet()),
                randomFromArr(headers), randomFromArr(bodies), randomFromArr(dates));
    }
}
