package com.dekinci.lksbstu.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {
    public static String capitalize(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static <T> T randomFromArr(T[] arr) {
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }

    public static Date ymdToDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
