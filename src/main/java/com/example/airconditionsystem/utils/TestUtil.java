package com.example.airconditionsystem.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 测试一些方法
 * @auther quewenjin
 * @time 2023/2/8 8:50
 */
public class TestUtil {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date222 = df.parse("2023-01-01 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date222);
        long tim = cal .getTimeInMillis();
        System.out.println(tim);
        System.out.println("1672502400000");

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        long time1 = date.getTime();
        System.out.println(time1);
        long time2 = time1 + 1000*60;
        System.out.println(time2);
        Timestamp timestamp = new Timestamp(time2);
        System.out.println(timestamp);

        System.out.println();

        Date date1 = new Date(time1);
        res = simpleDateFormat.format(date1);
        System.out.println(res);
        Date date2 = new Date(time2);
        res = simpleDateFormat.format(date2);
        System.out.println(res);

        System.out.println();

        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());

        for (int i = 0; i < 1; i++) {
            Random random = new Random();
            double num = random.nextDouble();// 看看几位
            System.out.println(num);

            num = num * 20 + 220;
            System.out.println(num);

            BigDecimal b = new BigDecimal(num);
            double num1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(num1);

            String result = String.format("%.1f", num);// 保留一位小数
            System.out.println(result);
        }
    }
}
