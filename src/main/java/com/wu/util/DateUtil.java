package com.wu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author z-yc
 * @date 2017-07-12 16:36:01
 */
public class DateUtil {
	
	public static final String PATTERN_DIGIT = "yyyyMMddHHmmss";

	public static final String PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat(PATTERN_DEFAULT);
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static String getSerialDate() {
		FORMAT.applyPattern(PATTERN_DIGIT);
		return FORMAT.format(getNowDate());
	}


	/**
	 * 获取当前时间的昨天
	 * @param format
	 * @return
	 */
	public static Date getNowYesterday(String format){
		SimpleDateFormat fm=new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);//取当前日期的前一天.
		return  cal.getTime();
	}

	/**
	 * 获取当前时间的明天
	 * @param format
	 * @return
	 */
	public static  Date getNowTomorrow(String format){
		SimpleDateFormat fm=new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);//取当前日期的后一天.
		return  cal.getTime();
	}


	/**
	 * 获取当前时间的昨天 返回 00:00:00 至23:59:59 时间段的字符串
	 * @return
	 */
	public static String [] getNowYesterdayString(){
		SimpleDateFormat format=new SimpleDateFormat(DateUtil.PATTERN_YYYYMMDD);
		String startTime=format.format(DateUtil.getNowYesterday(DateUtil.PATTERN_YYYYMMDD))+" 00:00:00";
		String endTime=format.format(DateUtil.getNowYesterday(DateUtil.PATTERN_YYYYMMDD))+" 23:59:59";
		String [] array=new String[2];
		array[0]=startTime;
		array[1]=endTime;
		return array;
	}

	/**
	 * 取得明天凌晨时间
	 * @return
	 */
	public static Date getNowTomorrowTime(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,1);
		return cal.getTime();
	}
	public static void main(String[] args) {
		for (String str:getNowYesterdayString()) {
			System.out.println(str);
		}
	}
}

