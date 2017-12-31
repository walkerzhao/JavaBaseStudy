package com.tencent.javaBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeekCal {
	
	public static void main(String[] args) {
		getWeek("2017-12-25");
		getWeek("2017-12-26");
		getWeek("2017-12-27");
		getWeek("2017-12-28");
		getWeek("2017-12-29");
		getWeek("2017-12-30");
		getWeek("2018-12-31");
		getWeek("2018-01-01");
	}
	
	public static void getWeek(String date) {
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        

	        Calendar cal = Calendar.getInstance();
	        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
	        //cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
	        cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
	        cal.setTime(df.parse(date));
	        int week = cal.get(Calendar.WEEK_OF_YEAR);
	        System.out.println("date:"+date + " week:" + week);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
