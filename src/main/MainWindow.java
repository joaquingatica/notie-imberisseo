package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import data.GregorianInfo;
import data.ImladrisInfo;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;


import java.awt.Color;
import java.awt.Font;

import lib.ImladrisCalendar;

public class MainWindow {
	
	/* FRAME */
	private JFrame frame;
	
	/* GREGORIAN to IMLADRIS */
	private JTextField year;
	private JComboBox month;
	private JComboBox day;
	private JButton toImladris;
	private JTextPane resImladris;
	
	/* IMLADRIS to GREGORIAN */
	private JTextField loa;
	private JComboBox yen;
	private JComboBox period;
	private JComboBox dayOfLoa;
	private JButton toGregorian;
	private JTextPane resGregorian;
	
	/* Frame */
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/* Gregorian to Imladris */
	public JTextField getYear() {
		return year;
	}
	public void setYear(JTextField year) {
		this.year = year;
	}
	public JComboBox getMonth() {
		return month;
	}
	public void setMonth(JComboBox month) {
		this.month = month;
	}
	public JComboBox getDay() {
		return day;
	}
	public void setDay(JComboBox day) {
		this.day = day;
	}
	public JButton getToImladris() {
		return toImladris;
	}
	public void setToImladris(JButton toImladris) {
		this.toImladris = toImladris;
	}
	public JTextPane getResImladris() {
		return resImladris;
	}
	public void setResImladris(JTextPane resImladris) {
		this.resImladris = resImladris;
	}
	
	/* Imladris to Gregorian */
	public JTextField getLoa() {
		return loa;
	}
	public void setLoa(JTextField loa) {
		this.loa = loa;
	}
	public JComboBox getYen() {
		return yen;
	}
	public void setYen(JComboBox yen) {
		this.yen = yen;
	}
	public JComboBox getPeriod() {
		return period;
	}
	public void setPeriod(JComboBox period) {
		this.period = period;
	}
	public JComboBox getDayOfLoa() {
		return dayOfLoa;
	}
	public void setDayOfLoa(JComboBox dayOfLoa) {
		this.dayOfLoa = dayOfLoa;
	}
	public JButton getToGregorian() {
		return toGregorian;
	}
	public void setToGregorian(JButton toGregorian) {
		this.toGregorian = toGregorian;
	}
	public JTextPane getResGregorian() {
		return resGregorian;
	}
	public void setResGregorian(JTextPane resGregorian) {
		this.resGregorian = resGregorian;
	}
	
	
	
	/**
	 * Create the application.
	 */
	private static MainWindow instance = null;
	public static MainWindow getInstance() {
		if(instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	private MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Notië Imberissëo 1.0 beta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(521, 372);
		frame.setLocation(300,200);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(2dlu;default)"),
				ColumnSpec.decode("172px"),
				ColumnSpec.decode("32px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("55px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("32px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("16px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(12dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblYear = new JLabel("Year");
		panel_1.add(lblYear, "5, 4, 3, 1, center, default");
		
		JLabel lblMonth = new JLabel("Month");
		panel_1.add(lblMonth, "9, 4, 5, 1, center, default");
		
		JLabel lblDay = new JLabel("Day");
		panel_1.add(lblDay, "15, 4, 3, 1, center, default");
		
		JLabel lblChooseGregorianDate = new JLabel("Choose Gregorian date:");
		panel_1.add(lblChooseGregorianDate, "2, 6, 2, 1, fill, fill");
		
		year = new JTextField();
		year.setToolTipText("Valid years: 1 - 2299");
		year.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				updateDayComboGregorian();
			}
		});
		panel_1.add(year, "5, 6, 3, 1, fill, fill");
		
		month = new JComboBox();
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateDayComboGregorian();
			}
		});
		month.setModel(new DefaultComboBoxModel(GregorianInfo.getInstance().getMonthsArray()));
		panel_1.add(month, "9, 6, 5, 1, fill, fill");
		
		day = new JComboBox();
		day.setEnabled(false);
		panel_1.add(day, "15, 6, 3, 1, fill, fill");
		
		toImladris = new JButton(">>");
		toImladris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				convertToImladris();
			}
		});
		toImladris.setEnabled(false);
		panel_1.add(toImladris, "11, 8, 7, 1, fill, fill");
		
		JLabel lblResultingImladrisDate = new JLabel("Resulting Imladris date:");
		panel_1.add(lblResultingImladrisDate, "2, 12, 2, 1, fill, fill");
		
		resImladris = new JTextPane();
		panel_1.add(resImladris, "5, 12, 13, 1, fill, fill");
		
		JLabel lblFormat = new JLabel("(Format: Weekday, Period [Day#], Yén Loa)");
		lblFormat.setFont(new Font("Dialog", Font.BOLD, 10));
		panel_1.add(lblFormat, "5, 14, 13, 1, fill, fill");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(87dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(13dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(8dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblYn = new JLabel("Yén");
		panel_2.add(lblYn, "6, 2, 3, 1, center, default");
		
		JLabel lblLoa = new JLabel("Loa");
		panel_2.add(lblLoa, "10, 2, 3, 1, center, default");
		
		JLabel lblPeriod = new JLabel("Period");
		panel_2.add(lblPeriod, "14, 2, 5, 1, center, default");
		
		JLabel lblNewLabel = new JLabel("Day");
		panel_2.add(lblNewLabel, "20, 2, 3, 1, center, default");
		
		JLabel lblS = new JLabel("Choose Imladris date:");
		panel_2.add(lblS, "2, 4, 3, 1, fill, fill");
		
		yen = new JComboBox();
		yen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateDayComboImladris();
			}
		});
		yen.setModel(new DefaultComboBoxModel(ImladrisInfo.getInstance().getYeniArray()));
		panel_2.add(yen, "6, 4, 3, 1, fill, fill");
		
		loa = new JTextField();
		loa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				updateDayComboImladris();
			}
		});
		loa.setToolTipText("Valid loar: 1 - 144");
		panel_2.add(loa, "10, 4, 3, 1, fill, fill");
		loa.setColumns(10);
		
		period = new JComboBox();
		period.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDayComboImladris();
			}
		});
		period.setModel(new DefaultComboBoxModel(ImladrisInfo.getInstance().getPeriodsArray()));
		panel_2.add(period, "14, 4, 5, 1, fill, fill");
		
		dayOfLoa = new JComboBox();
		dayOfLoa.setEnabled(false);
		panel_2.add(dayOfLoa, "20, 4, 3, 1, fill, fill");
		
		toGregorian = new JButton(">>");
		toGregorian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertToGregorian();
			}
		});
		toGregorian.setEnabled(false);
		panel_2.add(toGregorian, "16, 6, 7, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("Resulting Gregorian date:");
		panel_2.add(lblNewLabel_1, "2, 10, 3, 1, fill, fill");
		
		resGregorian = new JTextPane();
		panel_2.add(resGregorian, "6, 10, 17, 1, fill, fill");
		
		JLabel lblNewLabel_2 = new JLabel("(Format: Weekday, Month Day, Year)");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 10));
		panel_2.add(lblNewLabel_2, "6, 12, 17, 1");
	}
	
	private void updateDayComboGregorian() {
		JTextField year = this.getYear();
		JComboBox month = this.getMonth();
		JComboBox day = this.getDay();
		JButton convert = this.getToImladris();
		JTextPane result = this.getResImladris();
		String value = year.getText();
		if(!value.isEmpty()) {
			try {
				int yearNum = Integer.parseInt(value);
				if(yearNum > 0 && yearNum <= GregorianInfo.MAX_SUPPORTED_YEAR) {
					int monthNum = month.getSelectedIndex() + 1;
					int daySel = 0;
					if(day.isEnabled()) {
						daySel = day.getSelectedIndex() + 1;
					}
					ArrayList<Integer> days = GregorianInfo.getInstance().getDaysArray(yearNum, monthNum);
					day.setModel(new DefaultComboBoxModel(days.toArray()));
					if(daySel > 0 && daySel <= days.size()) {
						day.setSelectedIndex(daySel-1);
					}
					day.setEnabled(true);
					convert.setEnabled(true);
					result.setText("");
				}
				else {
					day.setEnabled(false);
					convert.setEnabled(false);
					day.setModel(new DefaultComboBoxModel());
					result.setText("");
				}
			}
			catch(NumberFormatException e) {
				day.setEnabled(false);
				convert.setEnabled(false);
				day.setModel(new DefaultComboBoxModel());
				result.setText("");
			}
		}
		else {
			day.setEnabled(false);
			convert.setEnabled(false);
			day.setModel(new DefaultComboBoxModel());
			result.setText("");
		}
	}
	private void convertToImladris() {
		String errorEmptyYear = "Please insert a year.";
		String errorYearNotNumeric = "Please insert the year as a numeric value.";
		String errorYearNotValid = "Please insert a valid year (from 1 to "+Integer.toString(GregorianInfo.MAX_SUPPORTED_YEAR)+").";
		String errorDayNotRead = "Sorry, the day could not be read.";
		
		JTextField year = this.getYear();
		JComboBox month = this.getMonth();
		JComboBox day = this.getDay();
		JTextPane result = this.getResImladris();
		String value = year.getText();
		if(!value.isEmpty()) {
			try {
				int yearNum = Integer.parseInt(value);
				if(yearNum > 0 && yearNum <= GregorianInfo.MAX_SUPPORTED_YEAR) {
					int monthNum = month.getSelectedIndex() + 1;
					int dayNum = 0;
					if(day.isEnabled()) {
						dayNum = day.getSelectedIndex() + 1;
						ImladrisCalendar cal = new ImladrisCalendar(yearNum, monthNum, dayNum);
						result.setText(cal.toString());
					}
					else {
						result.setText(errorDayNotRead);
					}
				}
				else {
					result.setText(errorYearNotValid);
				}
			}
			catch(NumberFormatException e) {
				result.setText(errorYearNotNumeric);
			}
		}
		else {
			result.setText(errorEmptyYear);
		}
	}
	
	

	private void updateDayComboImladris() {
		JComboBox yen = this.getYen();
		JTextField loa = this.getLoa();
		JComboBox period = this.getPeriod();
		JComboBox day = this.getDayOfLoa();
		JButton convert = this.getToGregorian();
		JTextPane result = this.getResGregorian();
		int yenNum = yen.getSelectedIndex() + 1;
		String value = loa.getText();
		if(!value.isEmpty()) {
			try {
				int loaNum = Integer.parseInt(value);
				if(loaNum > 0 && loaNum <= 144) {
					int periodNum = period.getSelectedIndex() + 1;
					if(periodNum == ImladrisCalendar.YESTARE || periodNum == ImladrisCalendar.METTARE) {
						day.setEnabled(false);
						day.setModel(new DefaultComboBoxModel());
						convert.setEnabled(true);
						result.setText("");
					}
					else {
						int daySel = 0;
						if(day.isEnabled()) {
							daySel = day.getSelectedIndex()+1;
						}
						ArrayList<Integer> days = ImladrisInfo.getInstance().getDaysArray(yenNum, loaNum, periodNum);
						day.setModel(new DefaultComboBoxModel(days.toArray()));
						if(daySel > 0 && daySel <= days.size()) {
							day.setSelectedIndex(daySel-1);
						}
						day.setEnabled(true);
						convert.setEnabled(true);
						result.setText("");
					}
				}
				else {
					day.setEnabled(false);
					convert.setEnabled(false);
					day.setModel(new DefaultComboBoxModel());
					result.setText("");
				}
			}
			catch(NumberFormatException e) {
				day.setEnabled(false);
				convert.setEnabled(false);
				day.setModel(new DefaultComboBoxModel());
				result.setText("");
			}
		}
		else {
			day.setEnabled(false);
			convert.setEnabled(false);
			day.setModel(new DefaultComboBoxModel());
			result.setText("");
		}
	}
	private void convertToGregorian() {
		String errorNoLoa = "Please insert a loa.";
		String errorLoaNotNumeric = "Please insert the loa as a numeric value.";
		String errorYearNotValid = "Please insert a valid loa (from 1 to 144).";
		String errorDayNotRead = "Sorry, the day could not be read.";
		
		JComboBox yen = this.getYen();
		JTextField loa = this.getLoa();
		JComboBox period = this.getPeriod();
		JComboBox day = this.getDayOfLoa();
		JTextPane result = this.getResGregorian();
		int yenNum = yen.getSelectedIndex() + 1;
		String value = loa.getText();
		if(!value.isEmpty()) {
			try {
				int loaNum = Integer.parseInt(value);
				if(loaNum > 0 && loaNum <= 144) {
					int periodNum = period.getSelectedIndex() + 1;
					ImladrisCalendar cal = new ImladrisCalendar();
					boolean success = false;
					if(periodNum == ImladrisCalendar.YESTARE || periodNum == ImladrisCalendar.METTARE) {
						success = true;
						cal = new ImladrisCalendar(cal.intToRoman(yenNum), loaNum, periodNum);
					}
					else {
						int dayNum = 0;
						if(day.isEnabled()) {
							success = true;
							dayNum = day.getSelectedIndex() + 1;
							cal = new ImladrisCalendar(cal.intToRoman(yenNum), loaNum, periodNum, dayNum);
						}
						else {
							result.setText(errorDayNotRead);
						}
					}
					if(success) {
						GregorianCalendar gcal = cal.getGregorian();
						SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, yyyy");
						String gstr = sdf.format(gcal.getTime());
						result.setText(gstr);
					}
				}
				else {
					result.setText(errorYearNotValid);
				}
			}
			catch(NumberFormatException e) {
				result.setText(errorLoaNotNumeric);
			}
		}
		else {
			result.setText(errorNoLoa);
		}
	}
	

}
