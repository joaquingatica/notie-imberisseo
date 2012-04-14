import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class NotieImberisseo {
	
	private static int random(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ImladrisCalendar cal = null;
		GregorianCalendar gcal = null;
		
		/*int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int limit = 20;
		int randYear, randMonth, randDay;
		for(int i = 0; i < limit; i++) {
			randYear = random(1, 2299);
			randMonth = random(0, 11);
			randDay = random(1, months[randMonth]);
			cal = new ImladrisCalendar(randYear, randMonth+1, randDay);
			gcal = cal.getGregorian();
			
			// Print Imladris date
			System.out.print("Imladris date:  ");
			String str = cal.toString();
			System.out.print(str);
			
			System.out.print(" | ");
			
			// Print Gregorian date
			System.out.print("Gregorian date:  ");
			SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, yyyy");
			String gstr = sdf.format(gcal.getTime());
			System.out.println(gstr);
		}*/
		
		/* Leave ONLY the desired constructor uncommented to test */
		//cal = new ImladrisCalendar();
		//cal = new ImladrisCalendar(new GregorianCalendar());
		//cal = new ImladrisCalendar(2012, 4, 13);
		cal = new ImladrisCalendar("XIV", 141, ImladrisCalendar.YESTARE);
		//cal = new ImladrisCalendar("XIV", 140, ImladrisCalendar.TUILE, 4);

		gcal = cal.getGregorian();
		
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
		System.out.print("Imladris date:  ");
		String str = cal.toString();
		System.out.println(str);
		
		// Print Gregorian date
		System.out.print("Gregorian date:  ");
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, yyyy");
		String gstr = sdf.format(gcal.getTime());
		System.out.println(gstr);
	}
	
}
