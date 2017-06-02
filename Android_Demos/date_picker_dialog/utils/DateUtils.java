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

    private DateUtils() {
        calendar = Calendar.getInstance();
    }

    public static DateUtils getInstance() {
        return new DateUtils();
    }
    /*
        将日期的字符串转换为字符数组
     */
    public char[] getDateCharArray() {
        String dateStr = formatDate(getYear(), getMonth(), getDay());
        return dateStr.toCharArray();
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
        初始化日期
     */
    public void initDate() {
        setYear(calendar.get(Calendar.YEAR));
        setMonth(calendar.get(Calendar.MONTH));
        setDay(calendar.get(Calendar.DAY_OF_MONTH));
    }
    /*
        设置日期
     */
    public void setDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    private void setDay(int day) {
        this.day = day;
    }
}
