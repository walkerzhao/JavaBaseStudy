package com.tencent.javaBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 计算日期属于一年的周数
 * 
result:
date:2017-12-25 week:52
date:2017-12-26 week:53
date:2017-12-27 week:53
date:2017-12-28 week:53
date:2017-12-29 week:53
date:2017-12-30 week:53
date:2017-12-31 week:53
date:2018-01-01 week:53
date:2018-01-02 week:1
date:2018-01-03 week:1
date:2018-01-04 week:1
date:2018-01-05 week:1
 * @author ewanzhao
 *
 */
public class WeekCal {
	
	public static void main(String[] args) {		
		
		getWeek("2017-12-25");
		getWeek("2017-12-26");
		getWeek("2017-12-27");
		getWeek("2017-12-28");
		getWeek("2017-12-29");
		getWeek("2017-12-30");
		getWeek("2017-12-31");
		getWeek("2018-01-01");
		getWeek("2018-01-02");
		getWeek("2018-01-03");
		getWeek("2018-01-04");
		getWeek("2018-01-05");
	}
	
	public static void getWeek(String date) {
		try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        
	        Calendar cal = Calendar.getInstance();
	        cal.setFirstDayOfWeek(Calendar.TUESDAY); // 设置每周的第一天为星期一
	        cal.setMinimalDaysInFirstWeek(2); // 新的一年第一周至少有N天在新年里才属于第一周
	        cal.setTime(df.parse(date));
	        int week = cal.get(Calendar.WEEK_OF_YEAR);
	        System.out.println("date:"+date + " week:" + week);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
