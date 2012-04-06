/**
 * ImladrisCalendar.java
 * Version 1.0 beta
 * Description: convert Gregorian date into Imladris Reckoning date.
 * 
 * Copyright 2012 Joaquín Gatica <http://twitter.com/joaquingatica>.
 * 
 * Special thanks to Erunno Alcarinollo <http://quenya101.com>.
 * 
 * Distributed under the terms of the GNU General Public License.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * To see a copy of the GNU General Public License, please
 * go to <http://www.gnu.org/licenses/gpl.txt>.
 * 
 */

import java.util.GregorianCalendar;

public class ImladrisCalendar {
	
	/**
	 * FIELDS
	 * */
	public static final int YEN = 0;
	public static final int LOA = 1;
	public static final int DAY_OF_LOA = 2;
	public static final int PERIOD = 3;
	public static final int DAY_OF_PERIOD = 4;
	public static final int DAY_OF_WEEK = 5;
	public static final int WEEK_OF_PERIOD = 6;
	
	/**
	 * PERIODS
	 */
	public static final int YESTARE = 1;
	public static final int TUILE = 2;
	public static final int LAIRE = 3;
	public static final int YAVIE = 4;
	public static final int ENDERI = 5;
	public static final int QUELLE = 6;
	public static final int HRIVE = 7;
	public static final int COIRE = 8;
	public static final int METTARE = 9;
	
	/**
	 * DAYS OF WEEK
	 */
	public static final int ELENYA = 1;
	public static final int ANARYA = 2;
	public static final int ISILYA = 3;
	public static final int ALDUNYA = 4;
	public static final int MENELYA = 5;
	public static final int VALANYA = 6;
	
	/**
	 * OTHER
	 */
	private static final String[] PERIODS_OF_LOA = {"Yestarë", "Tuilë", "Lairë", "Yávië", "Ender", "Quellë", "Hrívë", "Coirë", "Mettarë"};
	private static final int[] LENGTH_OF_PERIODS = {1, 54, 72, 54, 3, 54, 72, 54, 1};
	private static final int[] LENGTH_OF_PERIODS_LEAP = {1, 54, 72, 54, 6, 54, 72, 54, 1};
	private static final String[] DAYS_OF_WEEK = {"Elenya", "Anarya", "Isilya", "Aldúya", "Menelya", "Valanya"};
	private static final int loa0yenIdayOfWeek = ELENYA;
	private static final int[][][] YESTARE_MAP = {
		{{1, 144, 26}}, // YEN I
		{{1, 144, 26}}, // YEN II
		{{1, 144, 26}}, // YEN III
		{{1, 144, 23}}, // YEN IV
		{{1, 144, 23}}, // YEN V
		{{1, 144, 23}}, // YEN VI
		{{1, 144, 20}}, // YEN VII
		{{1, 144, 20}}, // YEN VIII
		{{1, 144, 20}}, // YEN IX
		{{1, 144, 17}}, // YEN X
		{{1, 142, 17}, {143, 144, 27}}, // YEN XI
		{{1, 115, 27}, {116, 144, 28}}, // YEN XII
		{{1, 71, 25}, {72, 144, 26}}, // YEN XIII
		{{1, 27, 26}, {28, 144, 27}}, // YEN XIV
		{{1, 83, 27}, {84, 144, 28}}, // YEN XV
		{{1, 39, 25}, {40, 139, 26}, {140, 144, 26}} // YEN XVI (140-144 are uncertain)
	};
	
	/**
	 * ATRIBUTES
	 */
	private GregorianCalendar gregorian;
	private int yenInt;
	private String yen;
	private int loa;
	private int[][][] yestareMap;
	private int loaBeginingDay;
	private int dayOfLoa;
	private boolean leapLoa;
	private int periodOfLoaInt;
	private String periodOfLoa;
	private boolean inMonth;
	private int monthOfLoa;
	private int dayOfPeriod;
	private int weekOfPeriod;
	private int dayOfWeekInt;
	private String dayOfWeek;
	
	/**
	 * Getters and setters
	 */
	public GregorianCalendar getGregorian() {
		return gregorian;
	}
	public void setGregorian(GregorianCalendar gregorian) {
		this.gregorian = gregorian;
	}
	
	public int getYenInt() {
		return yenInt;
	}
	public String getYen() {
		return this.yen;
	}
	public int getLoa() {
		return loa;
	}
	public int[][][] getYestareMap() {
		return yestareMap;
	}
	public int getLoaBeginingDay() {
		return loaBeginingDay;
	}
	public int getDayOfLoa() {
		return dayOfLoa;
	}
	public boolean isLeapLoa() {
		return leapLoa;
	}
	public int getPeriodOfLoaInt() {
		return periodOfLoaInt;
	}
	public String getPeriodOfLoa() {
		return periodOfLoa;
	}
	public boolean isInMonth() {
		return inMonth;
	}
	public int getMonthOfLoa() {
		return monthOfLoa;
	}
	public int getDayOfPeriod() {
		return dayOfPeriod;
	}
	public int getWeekOfPeriod() {
		return weekOfPeriod;
	}
	public int getDayOfWeekInt() {
		return dayOfWeekInt;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setYenInt(int yenInt) {
		this.setYen(this.intToRoman(yenInt));
		this.yenInt = yenInt;
	}
	public void setYen(String yen) {
		this.yen = yen;
	}
	public void setLoa(int loa) {
		this.loa = loa;
	}
	public void setYestareMap(int[][][] yestareMap) {
		this.yestareMap = yestareMap;
	}
	public void setLoaBeginingDay(int loaBeginingDay) {
		this.loaBeginingDay = loaBeginingDay;
	}
	public void setDayOfLoa(int dayOfLoa) {
		this.dayOfLoa = dayOfLoa;
	}
	public void setLeapLoa(boolean leapLoa) {
		this.leapLoa = leapLoa;
	}
	public void setPeriodOfLoaInt(int periodOfLoaInt) {
		this.setPeriodOfLoa(PERIODS_OF_LOA[periodOfLoaInt-1]);
		this.periodOfLoaInt = periodOfLoaInt;
	}
	public void setPeriodOfLoa(String periodOfLoa) {
		this.periodOfLoa = periodOfLoa;
	}
	public void setInMonth(boolean inMonth) {
		this.inMonth = inMonth;
	}
	public void setMonthOfLoa(int monthOfLoa) {
		this.monthOfLoa = monthOfLoa;
	}
	public void setDayOfPeriod(int dayOfPeriod) {
		this.dayOfPeriod = dayOfPeriod;
	}
	public void setWeekOfPeriod(int weekOfPeriod) {
		this.weekOfPeriod = weekOfPeriod;
	}
	public void setDayOfWeekInt(int dayOfWeekInt) {
		this.setDayOfWeek(DAYS_OF_WEEK[dayOfWeekInt-1]);
		this.dayOfWeekInt = dayOfWeekInt;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	
	/**
	 * Constructors
	 */
	public ImladrisCalendar() {
		this(new GregorianCalendar());
	}
	public ImladrisCalendar(GregorianCalendar gregorian) {
		this.setGregorian(new GregorianCalendar(gregorian.get(GregorianCalendar.YEAR), gregorian.get(GregorianCalendar.MONTH), gregorian.get(GregorianCalendar.DAY_OF_MONTH)));
		this.convert();
	}
	/**
	 * @param year
	 * @param month: index 1-12
	 * @param dayOfMonth
	 */
	public ImladrisCalendar(int year, int month, int dayOfMonth) {
		this.setGregorian(new GregorianCalendar(year, month-1, dayOfMonth));
		this.convert();
	}
	
	/**
	 * API Methods
	 */
	public boolean before(ImladrisCalendar cal) {
		return !this.same(cal) && !this.after(cal);
	}
	public boolean same(ImladrisCalendar cal) {
		boolean same = false;
		if(this.getYenInt() == cal.getYenInt() && this.getLoa() == cal.getLoa() && this.getDayOfLoa() == cal.getDayOfLoa())
			same = true;
		return same;
	}
	public boolean after(ImladrisCalendar cal) {
		boolean after = false;
		if(this.getYenInt() < cal.getYenInt()) {
			after = true;
		}
		else if(this.getYenInt() == cal.getYenInt()) {
			if(this.getLoa() < cal.getLoa()) {
				after = true;
			}
			else if(this.getLoa() == cal.getLoa()) {
				if(this.getDayOfLoa() < cal.getDayOfLoa()) {
					after = true;
				}
			}
		}
		return after;
	}
	public int get(int field) {
		int value = -1;
		switch(field) {
			case YEN:
				value = this.getYenInt();
			break;
			case LOA:
				value = this.getLoa();
			break;
			case DAY_OF_LOA:
				value = this.getDayOfLoa();
			break;
			case PERIOD:
				value = this.getPeriodOfLoaInt();
			break;
			case DAY_OF_PERIOD:
				value = this.getDayOfPeriod();
			break;
			case DAY_OF_WEEK:
				value = this.getDayOfWeekInt();
			break;
			case WEEK_OF_PERIOD:
				value = this.getWeekOfPeriod();
			break;
			default:
			break;
		}
		return value;
	}
	/**
	 * Add 'amount' to 'field'.
	 * Precondition: adding 'amount' must not overflow current 'field' state
	 * @param field
	 * @param amount
	 */
	public void add(int field, int amount) {
		switch(field) {
			case YEN:
				this.setYenInt(this.getYenInt()+amount);
				this.updateGregorian();
			break;
			case LOA:
				int loa = this.getLoa()+amount;
				this.add(YEN, (int)Math.floor(loa/144));
				this.setLoa(loa % 144);
				this.updateGregorian();
			break;
			case PERIOD:
			break;
			case DAY_OF_LOA:
			case DAY_OF_PERIOD:
			case DAY_OF_WEEK:
				int dayOfLoa = this.getDayOfLoa()+amount;
				this.add(LOA, (int)Math.floor(dayOfLoa/365));
				this.setDayOfLoa(dayOfLoa % 365);
				this.update();
			break;
			case WEEK_OF_PERIOD:
				this.add(DAY_OF_LOA, amount*6);
			break;
			default:
			break;
		}
	}
	public int lengthOfPeriod(int period) {
		int[] lengths = LENGTH_OF_PERIODS;
		if(this.isLeapLoa()) {
			lengths = LENGTH_OF_PERIODS_LEAP;
		}
		return lengths[period-1];
	}
	public void reconvert() {
		this.calculate(this.getGregorian());
	}
	
	/**
	 * 
	 * INTERNAL Methods
	 * 
	 */
	private void convert() {
		this.calculate(this.getGregorian());
	}
	
	/**
	 * With yen, loa and dayOfLoa set, update the rest
	 */
	private void update() {
		int yen = this.getYenInt();
		int loa = this.getLoa();
		int dayOfLoa = this.getDayOfLoa();
		// initialize data variables
		int loaBeg = 0;
		boolean isLeapLoa = false;
		int period = 0;
		int dayOfPeriod = 0;
		boolean isMonth = false;
		int month = 0;
		int weekOfPeriod = 0;
		int dayOfWeek = 0;
		
		int y = yen*loa;
		// calculate day of march of year 'y' in which loa begins
		loaBeg = this.loaBeginingDay(y);
		// calculate if is leap loa
		isLeapLoa = this.leapLoa(loa);
		// calculate current month and day of month
		int[] periodInfo = this.periodAndDayInPeriod(dayOfLoa, isLeapLoa);
		period = periodInfo[0];
		dayOfPeriod = periodInfo[1];
		// calculate current week in month (if any), and day of week
		if(this.periodIsMonth(period)) {
			isMonth = true;
			month = this.monthFromPeriod(period);
		}
		int[] weekInfo = this.weekAndDayOfWeek(loa, dayOfLoa);
		weekOfPeriod = weekInfo[0];
		dayOfWeek = weekInfo[1];
		// store data and return
		this.setLoaBeginingDay(loaBeg);
		this.setLeapLoa(isLeapLoa);
		this.setPeriodOfLoaInt(period);
		this.setDayOfPeriod(dayOfPeriod);
		this.setInMonth(isMonth);
		this.setMonthOfLoa(month);
		this.setWeekOfPeriod(weekOfPeriod);
		this.setDayOfWeekInt(dayOfWeek);
		
		this.updateGregorian();
	}
	private void updateGregorian() {
		int yen = this.getYenInt();
		int loa = this.getLoa();
		int dayOfLoa = this.getDayOfLoa();
		GregorianCalendar cal = this.getGregorian();
		cal.set(GregorianCalendar.YEAR, yen*loa);
		cal.set(GregorianCalendar.MONTH, GregorianCalendar.MARCH);
		cal.set(GregorianCalendar.DAY_OF_MONTH, this.getLoaBeginingDay());
		cal.add(GregorianCalendar.DAY_OF_YEAR, dayOfLoa);
	}
	private void calculate(GregorianCalendar cal) {
		// initialize data variables
		int yen = 0;
		int loa = 0;
		int loaBeg = 0;
		int daysOfLoa = 0;
		boolean isLeapLoa = false;
		int period = 0;
		int dayOfPeriod = 0;
		boolean isMonth = false;
		int month = 0;
		int weekOfPeriod = 0;
		int dayOfWeek = 0;
		
		int y = cal.get(GregorianCalendar.YEAR);
		// calculate day of march of year 'y' in which loa begins
		loaBeg = this.loaBeginingDay(y);
		GregorianCalendar loaCal = new GregorianCalendar(y, GregorianCalendar.MARCH, loaBeg);
		// if that day hasn't come yet, use previous year's loa
		if(cal.before(loaCal)) {
			y = y-1;
			loaBeg = this.loaBeginingDay(y);
		}
		// calculate yen
		yen = this.calcYen(y);
		// calculate loa
		loa = this.calcLoa(y);
		// calculate if is leap loa
		isLeapLoa = this.leapLoa(loa);
		// get amount of days of ongoing loa
		loaCal.set(GregorianCalendar.YEAR, y);
		loaCal.set(GregorianCalendar.DAY_OF_MONTH, loaBeg);
		daysOfLoa = this.daysBetweenGregorian(loaCal, cal);
		// calculate current month and day of month
		int[] periodInfo = this.periodAndDayInPeriod(daysOfLoa, isLeapLoa);
		period = periodInfo[0];
		dayOfPeriod = periodInfo[1];
		// calculate current week in month (if any), and day of week
		if(this.periodIsMonth(period)) {
			isMonth = true;
			month = this.monthFromPeriod(period);
		}
		int[] weekInfo = this.weekAndDayOfWeek(loa, daysOfLoa);
		weekOfPeriod = weekInfo[0];
		dayOfWeek = weekInfo[1];
		// store data and return
		this.setYenInt(yen);
		this.setLoa(loa);
		this.setLoaBeginingDay(loaBeg);
		this.setDayOfLoa(daysOfLoa);
		this.setLeapLoa(isLeapLoa);
		this.setPeriodOfLoaInt(period);
		this.setDayOfPeriod(dayOfPeriod);
		this.setInMonth(isMonth);
		this.setMonthOfLoa(month);
		this.setWeekOfPeriod(weekOfPeriod);
		this.setDayOfWeekInt(dayOfWeek);
	}
	private int calcLoa(int y) {
		int loa;
		if((y != 0) && (y % 144 == 0))
			loa = 144;
		else
			loa = (y % 144);
		return loa;
	}
	private int calcYen(int y) {
		int yen = (int)Math.floor(y/144);
		if(this.calcLoa(y) != 144)
			yen = 1+yen;
		return yen;
	}
	private int loaBeginingDay(int y) {
		int loa = this.calcLoa(y);
		int yen = this.calcYen(y);
		int yestare = this.calculateYestare(yen, loa);
		return yestare;
	}
	private int calculateYestare(int yen, int loa) {
		int yestare;
		// get array for current yen
		int[][] yenMap = YESTARE_MAP[yen-1];
		// find period of yen for given loa, and get yestare for first range
		int yestareMap = -1;
		boolean exit = false;
		int i = 0;
		int[] loaMap;
		while(i < yenMap.length && !exit) {
			loaMap = yenMap[i];
			if((loaMap[0] <= loa) && (loa <= loaMap[1])) {
				yestareMap = loaMap[2];
				exit = true;
			}
			i++;
		}
		// calculate appropriate yestare
		int mod = loa % 12;
		if(mod == 0)
			yestare = yestareMap;
		else if(mod <= 3)
			yestare = yestareMap + 3;
		else if(mod <= 7)
			yestare = yestareMap + 2;
		else
			yestare = yestareMap + 1;
		return yestare;
	}
	private boolean leapLoa(int loa) {
		return loa % 12 == 0;
	}
	private int[] periodAndDayInPeriod(int days, boolean leap) {
		int[] daysInPeriod = {1,54,72,54,3,54,72,54,1};
		if(leap) // if is leap loa, add 3 days to Enderi
			daysInPeriod[4] = 6;
		int period = 1;
		boolean end = false;
		while(!end && (period < 10)) {
			if(days <= daysInPeriod[period-1]) { // is in period 'period'
				end = true;
			}
			else { // is not in period 'period'
				days -= daysInPeriod[period-1];
				period++;
			}
		}
		int[] ret = {period, days};
		return ret;
	}
	private boolean periodIsMonth(int period) {
		return (period > 1 && period < 5) || (period > 5 && period < 9);
	}
	private int monthFromPeriod(int period) {
		return (period < 4)? period - 1 : period - 2; 
	}
	private int[] weekAndDayOfWeek(int loa, int dayOfLoa) {
		// This Loa's Yestare Day Of Week
		int weekDayOfYestare = ImladrisCalendar.loa0yenIdayOfWeek;
		// Offset from 24-loa block
		int aux = loa % 24;
		// Offset from 12-loa block (in any)
		if(aux > 11) {
			weekDayOfYestare = ((weekDayOfYestare -1 + 3) % 6) + 1;
			aux -= 11;
		}
		// Remaining offset
		for(int i = 0; i <= aux; i++) { // pairOfBlocks is between 0 and 11
			weekDayOfYestare = (weekDayOfYestare - 1) - 1;
			if(weekDayOfYestare < 0) {
				weekDayOfYestare = 6 + weekDayOfYestare;
			}
			weekDayOfYestare += 1;
		}
		// Current day's day of week
		int dayOfWeekIfWasRegular = ((dayOfLoa-1) % 6) + 1;
		int dayOfWeek = (((dayOfWeekIfWasRegular - 1) + (weekDayOfYestare - 1)) % 6) + 1;
		// Week
		int week = 0;
		/*int week = 1+(int)Math.floor((dayOfLoa-1)/6);*/
		int[] ret = {week, dayOfWeek};
		return ret;
	}
	
	private int daysBetweenGregorian(final GregorianCalendar startDate, final GregorianCalendar endDate) {
		GregorianCalendar dateStart = (GregorianCalendar)startDate.clone();
		dateStart.set(GregorianCalendar.HOUR_OF_DAY, 0);
		dateStart.set(GregorianCalendar.MINUTE, 0);
		dateStart.set(GregorianCalendar.SECOND, 0);
		GregorianCalendar dateEnd = (GregorianCalendar)endDate.clone();
		dateEnd.set(GregorianCalendar.HOUR_OF_DAY, 0);
		dateEnd.set(GregorianCalendar.MINUTE, 0);
		dateEnd.set(GregorianCalendar.SECOND, 0);
		int daysBetween = 1;
		while (dateStart.before(dateEnd)) {
			daysBetween++;
			dateStart.add(GregorianCalendar.DAY_OF_YEAR, 1);
		}
		return daysBetween;
	}
	private String intToRoman(int num) {
		String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] ARABIC  = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String roman = "";
        for(int i = 0; i < ROMAN.length; i++) {
            while(num >= ARABIC[i]) {
            	num -= ARABIC[i];
                roman  += ROMAN[i];
            }
        }
        return roman;
	}
	
	public String toString() {
		String str = this.getDayOfWeek()+", ";
		if(this.isInMonth()) {
			str += this.getPeriodOfLoa()+" "+this.getDayOfPeriod()+", ";
		}
		else if(this.getPeriodOfLoaInt() == ImladrisCalendar.ENDERI) {
			str += this.getPeriodOfLoa()+" "+this.getDayOfPeriod()+", ";
		}
		else {
			str += this.getPeriodOfLoa()+", ";
		}
		str += this.getYen()+" "+this.getLoa();
		return str;
	}
	
}
