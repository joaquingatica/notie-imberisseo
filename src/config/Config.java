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

package config;

public class Config {
	
	private static String GOOGLE_MAPS_API_KEY = "AIzaSyAK9hXpQhpoPoF9hSEHIBcqACmJcbHtW8c";
	private static long RATE_LIMIT_INTERVAL = (long)500; // In milliseconds
	private static int CONNECTION_TIMEOUT = 3000; // In milliseconds
	
	public static String getGoogleMapsApiKey() {
		return GOOGLE_MAPS_API_KEY;
	}
	public static long getRateLimitInterval() {
		return RATE_LIMIT_INTERVAL;
	}
	public static int getConnectionTimeout() {
		return CONNECTION_TIMEOUT;
	}
	
	
}
