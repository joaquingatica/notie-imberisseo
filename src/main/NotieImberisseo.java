package main;

import geo.google.GeoAddressStandardizer;
import geo.google.GeoException;
import geo.google.datamodel.GeoCoordinate;

import java.awt.EventQueue;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
<<<<<<< HEAD

import config.Config;
=======
>>>>>>> f430aa0... Added test for geolocation

import config.Config;


public class NotieImberisseo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Location location = null;
		
<<<<<<< HEAD
<<<<<<< HEAD
		GeoAddressStandardizer st = new GeoAddressStandardizer(new Config().getGoogleMapsApiKey());
=======
		GeoAddressStandardizer st = new GeoAddressStandardizer("AIzaSyAK9hXpQhpoPoF9hSEHIBcqACmJcbHtW8c");
>>>>>>> f430aa0... Added test for geolocation
=======
		GeoAddressStandardizer st = new GeoAddressStandardizer(new Config().getGoogleMapsApiKey());
>>>>>>> 1b9cdc1... Added config files and removes old libs
		try {
			GeoCoordinate geo = st.standardizeToGeoCoordinate("Montevideo, Uruguay");
			double latitude = geo.getLatitude();
			double longitude = geo.getLongitude();
			location = new Location(Double.toString(latitude), Double.toString(longitude));
			System.out.println(latitude);
			System.out.println(longitude);
		} catch (GeoException e) {
			System.out.println(e.getMessage());
			location = new Location("-34.89030", "-56.06254");
		}
		
		TimeZone tz = TimeZone.getDefault();
		SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, tz.getID());
		String sunset = calculator.getOfficialSunsetForDate(Calendar.getInstance())+":00";
		Time time = Time.valueOf(sunset);
		System.out.println(time);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = MainWindow.getInstance();
					//window.getFrame().setVisible(true);
					window.getFrame().setTitle("Notië Imberissëo");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		/* Leave ONLY the desired constructor uncommented to test */
		//ImladrisCalendar cal = null;
		//cal = new ImladrisCalendar();
		//cal = new ImladrisCalendar(new GregorianCalendar());
		//cal = new ImladrisCalendar(2012, 4, 13);
		//cal = new ImladrisCalendar("XIV", 141, ImladrisCalendar.YESTARE);
		//cal = new ImladrisCalendar("XIV", 140, ImladrisCalendar.TUILE, 4);
		
		// Uncomment to print all attributes
		/*System.out.println("yen: "+cal.getYen());
		System.out.println("loa: "+cal.getLoa());
		System.out.println("loaBeg: "+cal.getLoaBeginingDay());
		System.out.println("dayOfLoa: "+cal.getDayOfLoa());
		System.out.println("isLeapLoa: "+cal.isLeapLoa());
		System.out.println("period: "+cal.getPeriodOfLoa());
		System.out.println("isInMonth: "+cal.isInMonth());
		System.out.println("month: "+cal.getMonthOfLoa());
		System.out.println("dayOfPeriod: "+cal.getDayOfPeriod());
		System.out.println("weekOfPeriod: "+cal.getWeekOfPeriod());
		System.out.println("dayOfWeek: "+cal.getDayOfWeek());
		System.out.println("dayOfWeek: "+cal.getYestareWeekDay());*/
		
		// Print Imladris date
		/*System.out.print("Imladris date:  ");
		String str = cal.toString();
		System.out.println(str);
		
		// Print Gregorian date
		GregorianCalendar gcal = cal.getGregorian();
		System.out.print("Gregorian date:  ");
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, yyyy");
		String gstr = sdf.format(gcal.getTime());
		System.out.println(gstr);*/
	}
	
}
