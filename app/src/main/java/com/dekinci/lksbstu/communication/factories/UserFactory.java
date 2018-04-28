package com.dekinci.lksbstu.communication.factories;

import com.dekinci.lksbstu.communication.structure.UserStatus;
import com.dekinci.lksbstu.communication.structure.pojos.User;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.dekinci.lksbstu.utils.Utils.randomFromArr;

public class UserFactory {
    private static final AtomicInteger ids = new AtomicInteger(1);

    public static final String[] names = {"Кирилл", "Григорий", "Владимир", "Евгения", "Елизавета"};
    public static final String[] surnames = {"Товпека", "Зубрин", "Путин", "Лососева", "Арбузова"};
    public static final String[] patrons = {"Александрович", "Владиславович", "Владимирович", "Ашалайбовна", "Вольфовна"};
    private static final String[] institutes = {"ИКНТ"};
    private static final String[] educations = {"Бакалавр"};
    private static final String[] groups = {"135311", "43134", "43134", "43154"};
    private static final String[] enrollementDates = {"2007-12-03T10:15:30"};


    public User getStudent() {
        Random r = new Random();

        return new User(String.valueOf(ids.incrementAndGet()), randomFromArr(groups),
                randomFromArr(names), randomFromArr(surnames), randomFromArr(patrons),
                UserStatus.STUDENT.getStatus(), randomFromArr(institutes), randomFromArr(groups),
                randomFromArr(educations), User.Types.FULL_TIME, randomFromArr(enrollementDates),
                r.nextInt(4), r.nextInt(4));
    }

    public User getPrepod() {
        Random r = new Random();

        return new User(String.valueOf(ids.incrementAndGet()), randomFromArr(groups),
                randomFromArr(names), randomFromArr(surnames), randomFromArr(patrons),
                UserStatus.STUDENT.getStatus(), randomFromArr(institutes), "teacher",
                randomFromArr(educations), User.Types.FULL_TIME, randomFromArr(enrollementDates),
                r.nextInt(4), r.nextInt(4));
    }
}
