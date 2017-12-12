package br.com.rest.controlefacil.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PROGRAMAÇÃO on 06/12/2017.
 */

public class DateUtils {

    private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "br"));

    public static String format(){
        return format(getInstance());
    }

    public static String format(Date date) {
        return dateFormat.format(date);
    }

    public static String format(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return format(calendar.getTime());
    }

    public static String format(Calendar calendar) {
        return format(calendar.getTime());
    }

    public static Date format(String date){
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getInstance().getTime();
    }

    public static Calendar getInstance() {
        return Calendar.getInstance();
    }

    public static Calendar getInstance(int year, int month, int day) {
        Calendar calendar = getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

}
