package com.example.a61555.datepickerdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 61555 on 2017/6/2.
 */

public class DateUtils {

    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private static DateUtils instance;

    private DateUtils() {}

    public static DateUtils getInstance() {
        if (instance == null)
            instance = new DateUtils();
        return instance;
    }
    /*
        将日期的字符串转换为字符数组
     */
    public char[] getDateCharArray() {
        return (formatDate(year, month, day)).toCharArray();
    }
    /*
        将时间的字符串转换为字符数组
     */
    public char[] getTimeCharArray() {
        return (formatTime(hour, minute).toCharArray());
    }
    /*
        格式化日期
     */
    public String formatDate(int year, int month, int day) {
        Date date = new Date(year-1900, month, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    /*
        格式化详细日期（包含小时、分钟）
     */
    public String formatTime(int hour, int minute) {
        Date date = new Date(0, 0, 0, hour, minute);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return simpleDateFormat.format(date).substring(10);
    }
    /*
        初始化日期和时间
     */
    public void initDate() {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
    }
    /*
        设置日期
     */
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.day = day;
    }
    /*
        设置时间
     */
    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDay() {
        return day;
    }


    public int getHour() {
        return hour;
    }


    public int getMinute() {
        return minute;
    }

}
