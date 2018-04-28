package com.dekinci.lksbstu.utils;

import java.util.Random;

public class Utils {
    public static String capitalize(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static <T> T randomFromArr(T[] arr) {
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }
}
