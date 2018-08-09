package homework.smd.ru.financetracker.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static final String DATE_FORMAT = "dd.MM.yyyy";

	@SuppressLint("SimpleDateFormat")
	public static String fromDateToString(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}

	@SuppressLint("SimpleDateFormat")
	public static Date fromStringToDate(String str) {
		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
