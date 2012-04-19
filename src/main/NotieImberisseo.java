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
					MainWindow window = MainWindow.getInstance();
					window.getFrame().setVisible(true);
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
