package data;

import java.util.ArrayList;

import erutulco.utils.ImladrisCalendar;


public class ImladrisInfo {
	
	private static final String[] YENI = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII",
										  "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI"};
	private static final String[] PERIODS = {"Yestarë", "Tuilë", "Lairë", "Yávië", "Enderi",
											 "Quellë", "Hrívë", "Coirë", "Mettarë"};
	private static final int[] LENGTH_OF_PERIODS = {1, 54, 72, 54, 3, 54, 72, 54, 1};
	private static final int[] LENGTH_OF_PERIODS_LEAP = {1, 54, 72, 54, 6, 54, 72, 54, 1};
	
	
	private static ImladrisInfo instance = null;
	public static ImladrisInfo getInstance() {
		if(instance == null) {
			instance = new ImladrisInfo();
		}
		return instance;
	}
	private ImladrisInfo() {
		
	}
	

	public String[] getYeniArray() {
		return YENI;
	}
	public String[] getPeriodsArray() {
		return PERIODS;
	}
	public ArrayList<Integer> getDaysArray(int yen, int loa, int period) {
		ArrayList<Integer> days = new ArrayList<Integer>();
		ImladrisCalendar cal = new ImladrisCalendar();
		cal = new ImladrisCalendar(cal.intToRoman(yen), loa, period, 1);
		int[] length = LENGTH_OF_PERIODS;
		if(cal.isLeapLoa()) {
			length = LENGTH_OF_PERIODS_LEAP;
		}
		for(int i = 1; i <= length[period-1]; i++) {
			days.add(i);
		}
		return days;
	}
	
}
