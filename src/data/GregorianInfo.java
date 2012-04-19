package data;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GregorianInfo {
	
	public static final int MAX_SUPPORTED_YEAR = 2299;
	private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July",
											"August", "September", "October", "November", "December"};
	private static final int[] LENGTH_OF_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LENGTH_OF_MONTHS_LEAP = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	
	private static GregorianInfo instance = null;
	public static GregorianInfo getInstance() {
		if(instance == null) {
			instance = new GregorianInfo();
		}
		return instance;
	}
	private GregorianInfo() {
	}
	
	public String[] getMonthsArray() {
		return MONTHS;
	}
	public ArrayList<Integer> getDaysArray(int year, int month) {
		ArrayList<Integer> days = new ArrayList<Integer>();
		GregorianCalendar cal = new GregorianCalendar();
		int[] length = LENGTH_OF_MONTHS;
		if(cal.isLeapYear(year)) {
			length = LENGTH_OF_MONTHS_LEAP;
		}
		for(int i = 1; i <= length[month-1]; i++) {
			days.add(i);
		}
		return days;
	}
	
}
