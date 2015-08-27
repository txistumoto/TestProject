package org.sqs;

import java.util.Calendar;

public class TimestampUtil {
	
	public static java.sql.Timestamp getCurrentTimestamp() {
		//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(java.util.Calendar.getInstance().getTime().getTime());
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(new java.util.Date().getTime());
		return currentTimestamp;
	}
	
	/*
	 * Return dthe current timestamp with a adition of:
	 * 	desfase_mesure:			Calendar.HOUR
	 * 							Calendar.DATE
	 * 							Calendar.MONTH
	 * 							Calendar.YEAR
	 * 
	 * 	desfase_number			numbre of mesures
	 */
	
	public static java.sql.Timestamp getCurrentTimestamp(int desfase_mesure, int desfase_number) {
    	Calendar calendar = Calendar.getInstance();
      	calendar.add(desfase_mesure, desfase_number);
    	java.util.Date date = calendar.getTime();
		return  new java.sql.Timestamp(date.getTime());
	}
	
	public static String getDate(java.sql.Timestamp timestamp, java.util.Locale locale) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd",locale).format(timestamp);
	}
	


}
