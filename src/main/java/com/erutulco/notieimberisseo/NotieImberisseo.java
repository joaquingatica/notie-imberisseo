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

package com.erutulco.notieimberisseo;

import java.awt.EventQueue;

public class NotieImberisseo {

  /**
   * Main function.
   * @param args CLI arguments
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UserInterfaceController uic = UserInterfaceController.getInstance();
          uic.initializeWindow(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
