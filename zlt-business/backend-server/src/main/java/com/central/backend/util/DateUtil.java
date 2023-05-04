package com.central.backend.util;


import java.util.Calendar;
import java.util.Date;
public class DateUtil {


    //根据时间+天数
    public static Date getDate(Date date,Integer days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }
}
