package com.toolbox.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";

	private static DateFormat dateFormat = new SimpleDateFormat(
			Default_DATE_PATTERN);

	public static String formatDate(Date date) {
		return dateFormat.format((Date) date);
	}

	public static String formatDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format((Date) date);
	}

	public static Date parseDate(String dateStr) throws ParseException {
		return dateFormat.parse(dateStr);
	}

	public static Date parseDate(String dateStr, String format) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateStr);
	}
	
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		if(date!=null){
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.MONTH);
		}
		else{
			return 0;
		}
	}

	public static Date addYear(Date date, Float period) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, Math.round(12 * period));

		return c.getTime();
	}
}
