/**
 * Notië Imberissëo
 * <p>
 * Copyright 2012 Joaquín Gatica (Erutulco Eruntano)
 * <p>
 * Contact:
 * Twitter: <http://twitter.com/joaquingatica>
 * Email: <erutulco@quenya101.com>
 * <p>
 * This file is part of "Notië Imberissëo".
 * <p>
 * "Notië Imberissëo" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * "Notië Imberissëo" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with "Notië Imberissëo".  If not, see <http://www.gnu.org/licenses/>.
 */

package com.erutulco.notieimberisseo.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GregorianInfo {

  public static final int MAX_SUPPORTED_YEAR = 2299;
  private static final String[] MONTHS = {
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
  };
  private static final int[] LENGTH_OF_MONTHS = {
      31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
  };
  private static final int[] LENGTH_OF_MONTHS_LEAP = {
      31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
  };


  private static GregorianInfo instance = null;

  /**
   * Singleton method.
   * @return Singleton instance
   */
  public static GregorianInfo getInstance() {
    if (instance == null) {
      instance = new GregorianInfo();
    }
    return instance;
  }

  private GregorianInfo() {
  }

  public String[] getMonthsArray() {
    return MONTHS;
  }

  /**
   * Get days of month for a specific year.
   * @param year Desired year
   * @param month Desired month
   * @return Int array with the days of the month
   */
  public ArrayList<Integer> getDaysArray(int year, int month) {
    ArrayList<Integer> days = new ArrayList<Integer>();
    GregorianCalendar cal = new GregorianCalendar();
    int[] length = LENGTH_OF_MONTHS;
    if (cal.isLeapYear(year)) {
      length = LENGTH_OF_MONTHS_LEAP;
    }
    for (int i = 1; i <= length[month - 1]; i++) {
      days.add(i);
    }
    return days;
  }

}
