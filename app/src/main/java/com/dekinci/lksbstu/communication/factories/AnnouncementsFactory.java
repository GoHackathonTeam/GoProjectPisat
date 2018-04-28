package com.dekinci.lksbstu.communication.factories;

import com.dekinci.lksbstu.communication.structure.Announcement;

import java.util.concurrent.atomic.AtomicInteger;

import static com.dekinci.lksbstu.utils.Utils.randomFromArr;

public class AnnouncementsFactory {
    private static final AtomicInteger ids = new AtomicInteger(0);

    private static String[] headers = {"Кое-что случилось", "Внимание!!!", "Новости"};
    private static String[] bodies = {
            "Вы и сами наверное догадались, что <b>оповещения</b> это <i>оповещения</i>",
            "Очень важная информация",
            "Lorem ipsum lorem ipsum lorem ipsum blah blah blah"};

    private static String[] dates = {"2017.12.01 12:01"};
    private static String[] destinations = {"13531/4", "ИКНТ", "Все"};


    public Announcement generateNew() {
        return new Announcement(randomFromArr(UserFactory.names),
                randomFromArr(destinations), randomFromArr(headers), randomFromArr(bodies), randomFromArr(dates));
    }
}
