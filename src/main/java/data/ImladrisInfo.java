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

package data;

import com.erutulco.ImladrisCalendar;

import java.util.ArrayList;

public class ImladrisInfo {

  private static final String[] YENI = {
      "I", "II", "III", "IV", "V", "VI", "VII", "VIII",
      "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI"
  };
  private static final String[] PERIODS = {
      "Yestarë", "Tuilë", "Lairë", "Yávië", "Enderi",
      "Quellë", "Hrívë", "Coirë", "Mettarë"
  };
  private static final int[] LENGTH_OF_PERIODS = {
      1, 54, 72, 54, 3, 54, 72, 54, 1
  };
  private static final int[] LENGTH_OF_PERIODS_LEAP = {
      1, 54, 72, 54, 6, 54, 72, 54, 1
  };


  private static ImladrisInfo instance = null;

  /**
   * Singleton method.
   * @return Singleton instance
   */
  public static ImladrisInfo getInstance() {
    if (instance == null) {
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

  /**
   * Get days of period for a specific loa and yen.
   * @param yen Desired yen
   * @param loa Desired loa
   * @param period Desired period
   * @return Int array with the days of the period
   */
  public ArrayList<Integer> getDaysArray(int yen, int loa, int period) {
    ArrayList<Integer> days = new ArrayList<Integer>();
    ImladrisCalendar cal = new ImladrisCalendar();
    cal = new ImladrisCalendar(cal.intToRoman(yen), loa, period, 1);
    int[] length = LENGTH_OF_PERIODS;
    if (cal.isLeapLoa()) {
      length = LENGTH_OF_PERIODS_LEAP;
    }
    for (int i = 1; i <= length[period - 1]; i++) {
      days.add(i);
    }
    return days;
  }

}
