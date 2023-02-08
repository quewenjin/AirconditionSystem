package com.example.airconditionsystem.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @auther quewenjin
 * @time 2023/2/8 8:50
 */
public class TestUtil {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());
    }
}
