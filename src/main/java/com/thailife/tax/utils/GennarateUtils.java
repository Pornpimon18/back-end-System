package com.thailife.tax.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class GennarateUtils {
	
	public synchronized String getID()throws Exception{
    	SimpleDateFormat DATE_TIME_FORMAT=new SimpleDateFormat("yyMMddHHmmss");
		DATE_TIME_FORMAT.setTimeZone(TimeZone.getDefault());

		DATE_TIME_FORMAT = new SimpleDateFormat("yyMMddHHmmss");
		Random rand = new Random(); 
		int value = rand.nextInt(100000); 
		DecimalFormat df = new DecimalFormat("000000");
				
		String id = DATE_TIME_FORMAT.format(new Date())+df.format(value);

		return id;
 }
	
}
