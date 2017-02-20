package com.erutulco.notieimberisseo;

import com.erutulco.notieimberisseo.config.Config;
import com.erutulco.notieimberisseo.config.Settings;
import com.erutulco.notieimberisseo.data.GregorianInfo;
import com.erutulco.notieimberisseo.data.ImladrisInfo;
import com.erutulco.notieimberisseo.lang.Lang;
import com.erutulco.notieimberisseo.lang.LangManager;

import com.erutulco.utils.ImladrisCalendar;
import com.erutulco.utils.LocationInfo;
import com.erutulco.utils.SunsetUtils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class UserInterfaceController {

  private UserInterface ui;
  private LangManager langManager;

  public void setUi(UserInterface ui) {
    this.ui = ui;
  }

  public UserInterface getUi() {
    return ui;
  }

  public void setLangManager(LangManager langManager) {
    this.langManager = langManager;
  }

  public LangManager getLangManager() {
    return langManager;
  }

  private static UserInterfaceController instance = null;

  /**
   * Singleton method.
   * @return Singleton instance
   */
  public static UserInterfaceController getInstance() {
    if (instance == null) {
      instance = new UserInterfaceController();
    }
    return instance;
  }

  private UserInterfaceController() {
    LangManager langManager = LangManager.getInstance();
    Settings data = this.getCurrentSettings();
    String shortName = data.get(Settings.LANG);
    if (!langManager.defineLang(shortName)) {
      langManager.defineLang(data.getDefault(Settings.LANG));
    }
    this.setLangManager(langManager);
    String[] countryCity = this.getCurrentCountryAndCity();
    if (countryCity[0] != null && !countryCity[0].isEmpty()
        && countryCity[1] != null && !countryCity[1].isEmpty()) {
      data.setDefault(Settings.COUNTRY, countryCity[0]);
      data.setDefault(Settings.CITY, countryCity[1]);
    }
  }

  public void initializeWindow(boolean useCacheSunset) {
    this.createWindow();
    this.fillData(useCacheSunset);
  }

  /**
   * Initialize Window.
   * @param useCacheSunset Use sunset cache
   * @param selectedTab Index of selected tab
   */
  public void initializeWindow(boolean useCacheSunset, int selectedTab) {
    this.initializeWindow(useCacheSunset);
    JTabbedPane tabbedPane = UserInterface.getInstance().getTabbedPane();
    if (selectedTab >= 0 && selectedTab < tabbedPane.getTabCount()) {
      tabbedPane.setSelectedIndex(selectedTab);
    }
  }

  /**
   * Discard Window.
   */
  public void disposeWindow() {
    UserInterface ui = UserInterface.getInstance();
    ui.getFrame().dispose();
    UserInterface.disposeInstance();
  }

  public void reloadWindow() {
    this.disposeWindow();
    this.initializeWindow(true);
  }

  public void reloadWindow(int selectedTab) {
    this.disposeWindow();
    this.initializeWindow(true, selectedTab);
  }

  /**
   * Reload window in current tab.
   */
  public void reloadWindowInActualTab() {
    int index = UserInterface.getInstance().getTabbedPane().getSelectedIndex();
    this.disposeWindow();
    this.initializeWindow(true, index);
  }

  private void createWindow() {
    UserInterface ui = UserInterface.getInstance();
    ui.getFrame().setVisible(true);
    this.setUi(ui);
  }

  private void fillData(boolean useCacheSunset) {
    this.showDateOfToday(useCacheSunset);
    this.fillSettingsForm();
  }

  /**
   * Get current country and city.
   * @return Country, City string array
   */
  public String[] getCurrentCountryAndCity() {
    return this.getCurrentCountryAndCity(false);
  }

  /**
   * Get current country and city.
   * @param ignoreCache Ignore Cache
   * @return Country, City string array
   */
  public String[] getCurrentCountryAndCity(boolean ignoreCache) {
    String[] result = {};
    Settings data = this.getCurrentSettings();
    String ip = data.get(Settings.IP);
    if (ignoreCache || ip == null || ip.isEmpty()) {
      ip = SunsetUtils.getIpAddress();
      data.set(Settings.IP, ip);
      if (ip != null && !ip.isEmpty()) {
        result = SunsetUtils.getCountryAndCityFromIpAddress(ip);
      }
    }
    return result;
  }

  /**
   * Calculate sunset for actual location and time.
   * @return Time for the sunset
   */
  public Time calculateSunsetForActualLocation() {
    return this.calculateSunsetForActualLocation(true);
  }

  /**
   * Calculate sunset for actual location and time.
   * @param useCacheSunset Use sunset cache
   * @return Time for the sunset
   */
  public Time calculateSunsetForActualLocation(boolean useCacheSunset) {
    Settings data = this.getCurrentSettings();
    TimeZone tz = TimeZone.getTimeZone(data.get(Settings.TIMEZONE));
    return this.calculateSunsetForActualLocation(new GregorianCalendar(tz), useCacheSunset);
  }

  /**
   * Calculate sunset for current location.
   * @param calendar Calendar to use
   * @param useCacheSunset Use cache
   * @return Time of current sunset
   */
  public Time calculateSunsetForActualLocation(Calendar calendar, boolean useCacheSunset) {
    Settings data = this.getCurrentSettings();
    String city = data.get(Settings.CITY);
    String country = data.get(Settings.COUNTRY);
    return this.calculateSunset(calendar, city, country, useCacheSunset);
  }

  public Time calculateSunset(Calendar calendar, String city,
                                     String country, boolean useCacheSunset) {
    return calculateSunset(calendar, city, country, useCacheSunset, false);
  }

  /**
   * Calculate sunset for the provided parameters.
   * @param calendar Calendar to use
   * @param city City
   * @param country Country
   * @param useCacheSunset Use cache
   * @param forceCache Force cache use
   * @return Time of sunset
   */
  public Time calculateSunset(Calendar calendar, String city,
                                      String country, boolean useCacheSunset, boolean forceCache) {
    Time time = null;
    boolean useCache = useCacheSunset;
    Settings data = getCurrentSettings();
    if (!useCache) {
      try {
        LocationInfo info = SunsetUtils.getLocationInfo(city, country);
        if (info != null) {
          String sunset = SunsetUtils.calculateSunset(calendar, info);

          data.set(Settings.TIMEZONE, info.getTimeZone());
          data.set(Settings.SUNSET, sunset);

          time = Time.valueOf(sunset);
        } else {
          useCache = true;
        }
      } catch (Exception e) {
        useCache = true;
      }
    }
    if (useCache && !forceCache) {
      String cacheSunset = data.get(Settings.SUNSET);
      if (!cacheSunset.isEmpty()) {
        time = Time.valueOf(cacheSunset);
      } else {
        time = calculateSunset(calendar, city, country, false, true);
      }
    }
    return time;
  }

  /**
   * Show date of today.
   * @param useCacheSunset Use cache
   */
  public void showDateOfToday(boolean useCacheSunset) {
    Settings data = this.getCurrentSettings();
    String city = data.get(Settings.CITY);
    String country = data.get(Settings.COUNTRY);
    TimeZone tz = TimeZone.getTimeZone(data.get(Settings.TIMEZONE));
    GregorianCalendar gcal = new GregorianCalendar(tz);
    Time time = this.calculateSunsetForActualLocation(gcal, useCacheSunset);
    ImladrisCalendar cal;
    String sunsetStr = "";
    String locationInfo = "";
    if (time == null) {
      cal = new ImladrisCalendar(gcal);
    } else {
      cal = new ImladrisCalendar(time, gcal);
      /*check if before sunset*/
      String gtimeStr = gcal.get(GregorianCalendar.HOUR_OF_DAY) + ":"
          + gcal.get(GregorianCalendar.MINUTE) + ":" + gcal.get(GregorianCalendar.SECOND);
      Time gtime = Time.valueOf(gtimeStr);
      if (gtime.before(time)) { // before sunset
        sunsetStr = " - before sunset";
      } else { // after/during sunset
        sunsetStr = " - after sunset";
      }
      String sunsetTimeStr = time.toString();
      sunsetTimeStr = sunsetTimeStr.substring(0, sunsetTimeStr.length() - 3);
      sunsetStr += " " + Lang.Punctuation.parenthesis_open + "occurring at "
          + sunsetTimeStr + Lang.Punctuation.parenthesis_close;
      locationInfo = SunsetUtils.makeLocationString(city, country);
      if (locationInfo.length() > 0) {
        locationInfo = Lang.Punctuation.parenthesis_open + Lang.Common.location_label
            + Lang.Punctuation.double_dot + " " + locationInfo + " " + Lang.Punctuation.pipe + " "
            + Lang.Common.timezone_label + Lang.Punctuation.double_dot + " "
            + data.get(Settings.TIMEZONE) + Lang.Punctuation.parenthesis_close;
      }
    }
    String gstr = this.gregorianToString(gcal);
    String istr = cal.toString();
    UserInterface ui = this.getUi();
    ui.getTodayGregorian().setText(gstr + sunsetStr);
    ui.getTodayImladris().setText(istr);
    ui.getLocationInfo().setText(locationInfo);
  }


  /* ******* FROM GREGORIAN ********* */

  /**
   * Update Day Combo for Gregorian Calendar.
   */
  public void updateDayComboGregorian() {
    UserInterface window = this.getUi();

    JTextField year = window.getYear();
    JComboBox month = window.getMonth();
    JComboBox day = window.getDay();
    JButton convert = window.getToImladris();
    JTextPane result = window.getResImladris();
    String value = year.getText();
    if (!value.isEmpty()) {
      try {
        int yearNum = Integer.parseInt(value);
        if (yearNum > 0 && yearNum <= GregorianInfo.MAX_SUPPORTED_YEAR) {
          int monthNum = month.getSelectedIndex() + 1;
          int daySel = 0;
          if (day.isEnabled()) {
            daySel = day.getSelectedIndex() + 1;
          }
          ArrayList<Integer> days = GregorianInfo.getInstance().getDaysArray(yearNum, monthNum);
          day.setModel(new DefaultComboBoxModel(days.toArray()));
          if (daySel > 0 && daySel <= days.size()) {
            day.setSelectedIndex(daySel - 1);
          }
          day.setEnabled(true);
          convert.setEnabled(true);
          result.setText("");
        } else {
          day.setEnabled(false);
          convert.setEnabled(false);
          day.setModel(new DefaultComboBoxModel());
          result.setText("");
        }
      } catch (NumberFormatException e) {
        day.setEnabled(false);
        convert.setEnabled(false);
        day.setModel(new DefaultComboBoxModel());
        result.setText("");
      }
    } else {
      day.setEnabled(false);
      convert.setEnabled(false);
      day.setModel(new DefaultComboBoxModel());
      result.setText("");
    }
  }

  /**
   * Convert date to Imladris Reckoning.
   */
  @SuppressWarnings("deprecation")
  public void convertToImladris() {
    UserInterface window = this.getUi();

    String errorEmptyYear = "Please insert a year.";
    String errorYearNotNumeric = "Please insert the year as a numeric value.";
    String errorYearNotValid = "Please insert a valid year (from 1 to "
        + Integer.toString(GregorianInfo.MAX_SUPPORTED_YEAR) + ").";
    String errorDayNotRead = "Sorry, the day could not be read.";

    JTextField year = window.getYear();
    JComboBox month = window.getMonth();
    JComboBox day = window.getDay();
    JCheckBox afterSunset = window.getAfterSunset();
    JTextPane result = window.getResImladris();
    String value = year.getText();
    if (!value.isEmpty()) {
      try {
        int yearNum = Integer.parseInt(value);
        if (yearNum > 0 && yearNum <= GregorianInfo.MAX_SUPPORTED_YEAR) {
          int monthNum = month.getSelectedIndex() + 1;
          int dayNum = 0;
          if (day.isEnabled()) {
            dayNum = day.getSelectedIndex() + 1;
            ImladrisCalendar cal;
            if (afterSunset.isSelected()) {
              GregorianCalendar gcal = new GregorianCalendar(yearNum, monthNum, dayNum);
              Time time = this.calculateSunsetForActualLocation(gcal, true);
              cal = new ImladrisCalendar(time, yearNum, monthNum, dayNum, time.getHours(),
                  time.getMinutes(), time.getSeconds());
            } else {
              cal = new ImladrisCalendar(yearNum, monthNum, dayNum);
            }
            result.setText(cal.toString());
          } else {
            result.setText(errorDayNotRead);
          }
        } else {
          result.setText(errorYearNotValid);
        }
      } catch (NumberFormatException e) {
        result.setText(errorYearNotNumeric);
      }
    } else {
      result.setText(errorEmptyYear);
    }
  }

  /* ************ TO GREGORIAN ************ */

  /**
   * Update day combo for Imladris.
   */
  public void updateDayComboImladris() {
    UserInterface window = this.getUi();

    JComboBox yen = window.getYen();
    JTextField loa = window.getLoa();
    JComboBox period = window.getPeriod();
    JComboBox day = window.getDayOfLoa();
    JButton convert = window.getToGregorian();
    JTextPane result = window.getResGregorian();
    int yenNum = yen.getSelectedIndex() + 1;
    String value = loa.getText();
    if (!value.isEmpty()) {
      try {
        int loaNum = Integer.parseInt(value);
        if (loaNum > 0 && loaNum <= 144) {
          int periodNum = period.getSelectedIndex() + 1;
          if (periodNum == ImladrisCalendar.YESTARE || periodNum == ImladrisCalendar.METTARE) {
            day.setEnabled(false);
            day.setModel(new DefaultComboBoxModel());
            convert.setEnabled(true);
            result.setText("");
          } else {
            int daySel = 0;
            if (day.isEnabled()) {
              daySel = day.getSelectedIndex() + 1;
            }
            ArrayList<Integer> days = ImladrisInfo.getInstance()
                .getDaysArray(yenNum, loaNum, periodNum);
            day.setModel(new DefaultComboBoxModel(days.toArray()));
            if (daySel > 0 && daySel <= days.size()) {
              day.setSelectedIndex(daySel - 1);
            }
            day.setEnabled(true);
            convert.setEnabled(true);
            result.setText("");
          }
        } else {
          day.setEnabled(false);
          convert.setEnabled(false);
          day.setModel(new DefaultComboBoxModel());
          result.setText("");
        }
      } catch (NumberFormatException e) {
        day.setEnabled(false);
        convert.setEnabled(false);
        day.setModel(new DefaultComboBoxModel());
        result.setText("");
      }
    } else {
      day.setEnabled(false);
      convert.setEnabled(false);
      day.setModel(new DefaultComboBoxModel());
      result.setText("");
    }
  }

  /**
   * Convert date to Gregorian Calendar.
   */
  public void convertToGregorian() {
    UserInterface window = this.getUi();

    String errorNoLoa = "Please insert a loa.";
    String errorLoaNotNumeric = "Please insert the loa as a numeric value.";
    String errorYearNotValid = "Please insert a valid loa (from 1 to 144).";
    String errorDayNotRead = "Sorry, the day could not be read.";

    JComboBox yen = window.getYen();
    JTextField loa = window.getLoa();
    JComboBox period = window.getPeriod();
    JComboBox day = window.getDayOfLoa();
    JCheckBox beforeMidnight = window.getBeforeMidnight();
    JTextPane result = window.getResGregorian();
    int yenNum = yen.getSelectedIndex() + 1;
    String value = loa.getText();
    if (!value.isEmpty()) {
      try {
        int loaNum = Integer.parseInt(value);
        if (loaNum > 0 && loaNum <= 144) {
          int periodNum = period.getSelectedIndex() + 1;
          ImladrisCalendar cal = new ImladrisCalendar();
          boolean success = false;
          if (periodNum == ImladrisCalendar.YESTARE || periodNum == ImladrisCalendar.METTARE) {
            success = true;
            cal = new ImladrisCalendar(cal.intToRoman(yenNum), loaNum, periodNum);
          } else {
            int dayNum = 0;
            if (day.isEnabled()) {
              success = true;
              dayNum = day.getSelectedIndex() + 1;
              cal = new ImladrisCalendar(cal.intToRoman(yenNum), loaNum, periodNum, dayNum);
            } else {
              result.setText(errorDayNotRead);
            }
          }
          if (success) {
            GregorianCalendar gcal = cal.getGregorian();
            if (beforeMidnight.isSelected()) {
              gcal.add(GregorianCalendar.DAY_OF_YEAR, -1);
            }
            String gstr = this.gregorianToString(gcal);
            result.setText(gstr);
          }
        } else {
          result.setText(errorYearNotValid);
        }
      } catch (NumberFormatException e) {
        result.setText(errorLoaNotNumeric);
      }
    } else {
      result.setText(errorNoLoa);
    }
  }

  /* ************ SETTINGS *************** */

  private void fillSettingsForm() {
    UserInterface ui = this.getUi();
    Settings data = this.getCurrentSettings();
    /* Country and city */
    String city = data.get(Settings.CITY);
    String country = data.get(Settings.COUNTRY);
    ui.getCity().setText(city);
    ui.getCountry().setText(country);
    /* Lang */
    LangManager langManager = LangManager.getInstance();
    String lang = langManager.getDefinedLang().getShortName();
    JComboBox langsCombo = ui.getLangCombo();
    langsCombo.setSelectedIndex(Arrays.asList(LangManager.getLanguagesShort()).indexOf(lang));
  }

  /**
   * Save the settings.
   */
  public void saveSettings() {
    UserInterface ui = this.getUi();
    Settings data = this.getCurrentSettings();
    boolean updateDate = false;
    /* City, country, tz */
    String savedCity = data.get(Settings.CITY);
    String newCity = ui.getCity().getText();
    if (!savedCity.equals(newCity)) {
      data.set(Settings.CITY, newCity);
      updateDate = true;
    }
    String savedCountry = data.get(Settings.COUNTRY);
    String newCountry = ui.getCountry().getText();
    if (!savedCountry.equals(newCountry)) {
      data.set(Settings.COUNTRY, newCountry);
      updateDate = true;
    }
    /* Lang */
    LangManager langManager = LangManager.getInstance();
    String shortLang = LangManager.getLanguagesShort()[
          Arrays.asList(LangManager.getLanguagesPrintable()).indexOf(
              (String) ui.getLangCombo().getSelectedItem()
          )
        ];
    Lang actual = langManager.getDefinedLang();
    if (!actual.getShortName().equals(shortLang)) {
      langManager.defineLang(shortLang);
      data.set(Settings.SUNSET, shortLang);
      this.reloadWindowInActualTab();
      updateDate = false;
    }
    /* Update today's date */
    if (updateDate) {
      this.showDateOfToday(false);
    }
  }

  /* *********** OTHER **************** */

  private String gregorianToString(GregorianCalendar gcal) {
    SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, y");
    String gstr = sdf.format(gcal.getTime());
    return gstr;
  }

  private Settings getCurrentSettings() {
    return Config.getCurrentSettings();
  }


}
