package com.smart.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
/**
 * 时间工具类
 * @author LH
 * 2017年12月27日 15:29:44
 */
public class DateUtil {
	
	public static Logger log = Logger.getLogger(DateUtil.class);
	
	public static final String pattern_ymd = "yyyy-MM-dd";
	public static final String pattern_ymd_hms = "yyyy-MM-dd HH:mm:ss";
	public static final String pattern_ymd_hms_s = "yyyy-MM-dd HH:mm:ss:SSS";
	
	
	public static String format(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static String getDeviceDataTableName(Date date) {
        StringBuffer tableName = new StringBuffer("d_device_data_");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
        String dateStr = sdf1.format(date);
        tableName.append(dateStr).append("_");
        if (date.getTime() <= endDayTimeByDay(date, 10).getTime()) {
            tableName.append(1);
        } else if (date.getTime() <= endDayTimeByDay(date, 20).getTime()) {
            tableName.append(2);
        } else {
            tableName.append(3);
        }
        return tableName.toString();
    }

    public static Date endDayTimeByDay(Date dateTime, Integer day) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(dateTime);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, day);
        gcLast.set(Calendar.HOUR_OF_DAY, 23);
        gcLast.set(Calendar.MINUTE, 59);
        gcLast.set(Calendar.SECOND, 59);
        Date date = gcLast.getTime();
        return date;
    }
}
