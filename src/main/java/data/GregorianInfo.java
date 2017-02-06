/**
 * 
 * Notië Imberissëo
 * 
 * Copyright 2012 Joaquín Gatica (Erutulco Eruntano)
 * 
 * Contact:
 * 		Twitter: <http://twitter.com/joaquingatica>
 * 		Email: <erutulco@quenya101.com>
 * 
 * This file is part of "Notië Imberissëo".
 * 
 * "Notië Imberissëo" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "Notië Imberissëo" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "Notië Imberissëo".  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
