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

package main;

import java.awt.EventQueue;

public class NotieImberisseo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIController uic = UIController.getInstance();
					uic.initializeWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
