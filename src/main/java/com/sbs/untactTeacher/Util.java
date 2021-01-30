package com.sbs.untactTeacher;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String today()
	{
		Date today = new Date();
		System.out.println(today);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
		
		
		return date.format(today) + " " + time.format(today);
	}
}
