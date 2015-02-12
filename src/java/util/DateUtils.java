package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	public static boolean areEquals(Date date1, Date date2) {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		System.out.println("date1=" + date1);
		System.out.println("date2=" + date2);
		cal1.setTime(date1);
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
						.get(Calendar.DAY_OF_YEAR);
	}

	public static Date getMondayThisWeek() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return format(c.getTime());
	}

	private static Date format(Date date) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			return df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
