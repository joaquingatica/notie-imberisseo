package com.erutulco.notieimberisseo;

import com.erutulco.notieimberisseo.config.Config;
import com.erutulco.notieimberisseo.data.GregorianInfo;
import com.erutulco.notieimberisseo.data.ImladrisInfo;
import com.erutulco.notieimberisseo.lang.Lang;
import com.erutulco.notieimberisseo.lang.LangManager;

import com.erutulco.utils.ImladrisCalendar;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.GeocodingResult;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import com.maxmind.geoip.LookupService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.prefs.Preferences;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.commons.io.FileUtils;

public class UserInterfaceController {

  private static final String FIELD_CITY = "city";
  private static final String FIELD_COUNTRY = "country";
  private static final String FIELD_LANG = "language";
  private static final String FIELD_TIMEZONE = "timezone";
  private static final String FIELD_CACHE_SUNSET = "sunset";
  private static final String FIELD_IP = "ip";
  private static String DEF_COUNTRY = "Uruguay";
  private static String DEF_CITY = "Montevideo";
  private static final String DEF_LANG = "eng";
  private static final String DEF_TIMEZONE = TimeZone.getDefault().getID();
  private static final String DEF_CACHE_SUNSET = "";
  private static final String DEF_IP = "";

  private UserInterface ui;
  private Preferences preferences = null;
  private LangManager langManager;

  public void setUi(UserInterface ui) {
    this.ui = ui;
  }

  public UserInterface getUi() {
    return ui;
  }

  public void setPreferences(Preferences preferences) {
    this.preferences = preferences;
  }

  public Preferences getPreferences() {
    return preferences;
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
    Preferences data = this.getCurrentPreferences();
    String shortName = data.get(FIELD_LANG, DEF_LANG);
    if (!langManager.defineLang(shortName)) {
      langManager.defineLang(DEF_LANG);
    }
    this.setLangManager(langManager);
    String[] countryCity = this.getCurrentCountryAndCity();
    if (countryCity[0] != null && !countryCity[0].isEmpty()
        && countryCity[1] != null && !countryCity[1].isEmpty()) {
      DEF_COUNTRY = countryCity[0];
      DEF_CITY = countryCity[1];
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

  private String makeLocationString(String city, String country) {
    String place = "";
    if (city.length() > 0 || country.length() > 0) {
      if (city.length() > 0) {
        place += city;
      }
      if (country.length() > 0) {
        if (place.length() > 0) {
          place += ", ";
        }
        place += country;
      }
    }
    return place;
  }

  /**
   * Calculate sunset for actual location and time.
   * @param useCacheSunset Use sunset cache
   * @return Time for the sunset
   */
  public Time calculateSunsetForActualLocationAndTime(boolean useCacheSunset) {
    Preferences data = this.getCurrentPreferences();
    TimeZone tz = TimeZone.getTimeZone(data.get(FIELD_TIMEZONE, DEF_TIMEZONE));
    Time time = this.calculateSunsetForActualLocation(new GregorianCalendar(tz), useCacheSunset);
    return time;
  }

  public String getIpAddress() {
    return this.getIpAddress(false);
  }

  /**
   * Get current IP address.
   * @param ignoreCache Ignore cache
   * @return IP address string
   */
  public String getIpAddress(boolean ignoreCache) {
    Preferences data = this.getCurrentPreferences();
    String ip = data.get(FIELD_IP, DEF_IP);
    if (ignoreCache || ip == null || ip.isEmpty()) {
      try {
        URL url = new URL("https://api.ipify.org");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          out.append(line);
        }
        ip = out.toString();
        data.put(FIELD_IP, ip);
      } catch (IOException e) {
        System.err.println(e.getMessage());
      }
    }
    return ip;
  }

  /**
   * Calculate Country and City from IP Address.
   * @param ip IP address string
   * @return Country, City string array
   */
  public String[] getCountryAndCityFromIpAddress(String ip) {
    String[] res = new String[2];

    try {
      InputStream is = this.getClass().getResourceAsStream(
          "/com/erutulco/notieimberisseo/maxmind/GeoLiteCity.dat"
      );
      File tempFile = File.createTempFile("GeoLiteCity", ".dat");
      tempFile.deleteOnExit();
      FileUtils.copyInputStreamToFile(is, tempFile);

      LookupService cl = new LookupService(tempFile,
          LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
      com.maxmind.geoip.Location location = cl.getLocation(ip);
      res[0] = location.countryName;
      res[1] = location.city;
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    return res;
  }

  /**
   * Get current country and city.
   * @return Country, City string array
   */
  public String[] getCurrentCountryAndCity() {
    String ip = this.getIpAddress();
    String[] result = {};
    if (ip != null && !ip.isEmpty()) {
      result = this.getCountryAndCityFromIpAddress(ip);
    }
    return result;
  }

  /**
   * Calculate sunset for current location.
   * @param calendar Calendar to use
   * @param useCacheSunset Use cache
   * @return Time of current sunset
   */
  public Time calculateSunsetForActualLocation(Calendar calendar, boolean useCacheSunset) {
    Preferences data = this.getCurrentPreferences();
    String city = data.get(FIELD_CITY, DEF_CITY);
    String country = data.get(FIELD_COUNTRY, DEF_COUNTRY);
    Time time = this.calculateSunset(calendar, city, country, useCacheSunset);
    return time;
  }

  public Time calculateSunset(Calendar calendar, String city,
                              String country, boolean useCacheSunset) {
    return this.calculateSunset(calendar, city, country, useCacheSunset, false);
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
    Preferences data = this.getCurrentPreferences();
    if (!useCache) {
      String place = this.makeLocationString(city, country);
      if (place.length() > 0) {
        try {
          GeoApiContext context = new GeoApiContext().setApiKey(Config.getGoogleMapsApiKey());
          GeocodingResult[] results = GeocodingApi.geocode(context, place).await();
          TimeZone tz = TimeZoneApi.getTimeZone(context, results[0].geometry.location).await();
          data.put(FIELD_TIMEZONE, tz.getID());

          double latitude = results[0].geometry.location.lat;
          double longitude = results[0].geometry.location.lng;
          Location location = new Location(Double.toString(latitude), Double.toString(longitude));
          SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, tz);
          String sunset = calculator.getOfficialSunsetForDate(calendar) + ":00";
          data.put(FIELD_CACHE_SUNSET, sunset);

          time = Time.valueOf(sunset);
        } catch (Exception e) {
          useCache = true;
        }
      } else {
        useCache = true;
      }
    }
    if (useCache && !forceCache) {
      String cacheSunset = data.get(FIELD_CACHE_SUNSET, DEF_CACHE_SUNSET);
      if (!cacheSunset.isEmpty()) {
        time = Time.valueOf(cacheSunset);
      } else {
        time = this.calculateSunset(calendar, city, country, false, true);
      }
    }
    return time;
  }

  /**
   * Show date of today.
   * @param useCacheSunset Use cache
   */
  public void showDateOfToday(boolean useCacheSunset) {
    Preferences data = this.getCurrentPreferences();
    String city = data.get(FIELD_CITY, DEF_CITY);
    String country = data.get(FIELD_COUNTRY, DEF_COUNTRY);
    TimeZone tz = TimeZone.getTimeZone(data.get(FIELD_TIMEZONE, DEF_TIMEZONE));
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
      locationInfo = this.makeLocationString(city, country);
      if (locationInfo.length() > 0) {
        locationInfo = Lang.Punctuation.parenthesis_open + Lang.Common.location_label
            + Lang.Punctuation.double_dot + " " + locationInfo + " " + Lang.Punctuation.pipe + " "
            + Lang.Common.timezone_label + Lang.Punctuation.double_dot + " "
            + data.get(FIELD_TIMEZONE, DEF_TIMEZONE) + Lang.Punctuation.parenthesis_close;
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
    Preferences data = this.getCurrentPreferences();
    /* Country and city */
    String city = data.get(FIELD_CITY, DEF_CITY);
    String country = data.get(FIELD_COUNTRY, DEF_COUNTRY);
    ui.getCity().setText(city);
    ui.getCountry().setText(country);
    /* Lang */
    LangManager langManager = LangManager.getInstance();
    String lang = langManager.getDefinedLang().getShortName();
    JComboBox langsCombo = ui.getLangCombo();
    langsCombo.setSelectedIndex(Arrays.asList(LangManager.getLanguagesShort()).indexOf(lang));
    /* Time Zone */
    String[] tzs = TimeZone.getAvailableIDs();
    Arrays.sort(tzs);
    JList list = ui.getTimeZone();
    list.setListData(tzs);
    String timezone = data.get(FIELD_TIMEZONE, DEF_TIMEZONE);
    list.setSelectedValue(timezone, true);
  }

  /**
   * Save the settings.
   */
  public void saveSettings() {
    UserInterface ui = this.getUi();
    Preferences data = this.getCurrentPreferences();
    boolean updateDate = false;
    /* City, country, tz */
    String savedCity = data.get(FIELD_CITY, DEF_CITY);
    String newCity = ui.getCity().getText();
    if (!savedCity.equals(newCity)) {
      data.put(FIELD_CITY, newCity);
      updateDate = true;
    }
    String savedCountry = data.get(FIELD_COUNTRY, DEF_COUNTRY);
    String newCountry = ui.getCountry().getText();
    if (!savedCountry.equals(newCountry)) {
      data.put(FIELD_COUNTRY, newCountry);
      updateDate = true;
    }
    /* Time Zone */
    String savedTimezone = data.get(FIELD_TIMEZONE, DEF_TIMEZONE);
    String newTimezone = (String)ui.getTimeZone().getSelectedValue();
    if (!savedTimezone.equals(newTimezone)) {
      data.put(FIELD_TIMEZONE, newTimezone);
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
      data.put(FIELD_LANG, shortLang);
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

  private Preferences getCurrentPreferences() {
    Preferences prefs = this.getPreferences();
    if (prefs == null) {
      prefs = Preferences.userRoot().node(this.getClass().getName());
      this.setPreferences(prefs);
    }
    return prefs;
  }


}
