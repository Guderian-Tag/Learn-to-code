package com.line0.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public static String getHour(Date date){
		 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	     String dateStr = format.format(date);
	     String[] timeArr = dateStr.split(":");
	     String s = timeArr[0];
	     return s;
	}
	
	/**
	 * 普通订单 计算倒计时
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getMinutes(Date beginDate,String endDate){
		long minute = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			Date d2 = df.parse(endDate);
			long mils = d2.getTime()-calendar.getTimeInMillis();
			minute = mils/1000/60;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minute;
	}
	
	/**
	 * 预约单获取倒计时
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getMinutes2(Date beginDate,String endDate){
		long minutes = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			if(endDate!=null){
				//2013-06-19 12:00 - 12:30
				int index = endDate.lastIndexOf("-");
				String d2Str = endDate.substring(0, 10).trim();
				String d2Str2 = endDate.substring(index+1, endDate.length()).trim();
				d2Str =d2Str + " "+d2Str2;
				d2Str += ":00";
				Date d2 = df.parse(d2Str);
				long mils = d2.getTime()-calendar.getTimeInMillis();
				minutes = mils/1000/60;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minutes;
	}
}
