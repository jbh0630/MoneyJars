package com.example.moneyjars.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        String month = sdf.format(new Date());
        return month;
    }

    public static String getAddDate(String date, int addDate) {
        Calendar cal = new GregorianCalendar(Locale.CANADA);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_YEAR, addDate);

        String resultDate = sdf.format(cal.getTime());
        return resultDate;
    }

    public static String getAddMonth(String month, int addMonth) {
        Calendar cal = new GregorianCalendar(Locale.CANADA);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        try {
            cal.setTime(sdf.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, addMonth);

        String resultMonth = sdf.format(cal.getTime());
        return resultMonth;
    }

}
