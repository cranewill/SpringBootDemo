package com.tsuru.springboot_demo.utils;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeUtil {

	protected static Logger log = LogManager.getLogger(TimeUtil.class);

	/**
	 * 判断两个时间中间所差天数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int getBetweenDays(long time1, long time2) {
		Calendar instance1 = Calendar.getInstance();
		instance1.setTimeInMillis(time1);
		instance1.set(Calendar.HOUR_OF_DAY, 0);
		instance1.set(Calendar.MINUTE, 0);
		instance1.set(Calendar.SECOND, 0);
		instance1.set(Calendar.MILLISECOND, 0);
		Calendar instance2 = Calendar.getInstance();
		instance2.setTimeInMillis(time2);
		instance2.set(Calendar.HOUR_OF_DAY, 0);
		instance2.set(Calendar.MINUTE, 0);
		instance2.set(Calendar.SECOND, 0);
		instance2.set(Calendar.MILLISECOND, 0);
		return (int) ((instance1.getTimeInMillis() - instance2.getTimeInMillis()) / (24 * 60 * 60 * 1000));
	}
	
	public static boolean isSameDay(long time1, long time2) {
		return getDay(time1) == getDay(time2);
	}

	public static int getDay(long time) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(time);
		int year1 = cal1.get(Calendar.YEAR);
		int month1 = cal1.get(Calendar.MONTH) + 1;
		int day1 = cal1.get(Calendar.DATE);
		return year1 * 10000 + month1 * 100 + day1;
	}

}
