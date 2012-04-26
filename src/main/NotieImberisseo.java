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
