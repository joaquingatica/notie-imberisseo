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

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.prefs.Preferences;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.google.maps.TimeZoneApi;
import lang.Lang;
import lang.LangManager;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import config.Config;
import data.GregorianInfo;
import data.ImladrisInfo;
import com.erutulco.ImladrisCalendar;

import com.google.maps.GeocodingApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;

import com.maxmind.geoip.LookupService;

import org.apache.commons.io.FileUtils;

public class UIController {

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

    private UI ui;
    private Preferences preferences = null;
    private LangManager langManager;
    public void setUi(UI ui) {
        this.ui = ui;
    }
    public UI getUi() {
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

    private static UIController instance = null;
    public static UIController getInstance() {
        if(instance == null) {
            instance = new UIController();
        }
        return instance;
    }
    private UIController() {
        LangManager langManager = LangManager.getInstance();
        Preferences data = this.getCurrentPreferences();
        String shortName = data.get(FIELD_LANG, DEF_LANG);
        if(!langManager.defineLang(shortName)) {
            langManager.defineLang(DEF_LANG);
        }
        this.setLangManager(langManager);
        String[] countryCity = this.getCurrentCountryAndCity();
        if (countryCity[0] != null && !countryCity[0].isEmpty() && countryCity[1] != null && !countryCity[1].isEmpty()) {
            DEF_COUNTRY = countryCity[0];
            DEF_CITY = countryCity[1];
        }
    }

    public void initializeWindow(boolean useCacheSunset) {
        this.createWindow();
        this.fillData(useCacheSunset);
    }
    public void initializeWindow(boolean useCacheSunset, int selectedTab) {
        this.initializeWindow(useCacheSunset);
        JTabbedPane tabbedPane = UI.getInstance().getTabbedPane();
        if(selectedTab >= 0 && selectedTab < tabbedPane.getTabCount()) {
            tabbedPane.setSelectedIndex(selectedTab);
        }
    }
    public void disposeWindow() {
        UI ui = UI.getInstance();
        ui.getFrame().dispose();
        UI.disposeInstance();
    }
    public void reloadWindow() {
        this.disposeWindow();
        this.initializeWindow(true);
    }
    public void reloadWindow(int selectedTab) {
        this.disposeWindow();
        this.initializeWindow(true, selectedTab);
    }
    public void reloadWindowInActualTab() {
        int index = UI.getInstance().getTabbedPane().getSelectedIndex();
        this.disposeWindow();
        this.initializeWindow(true, index);
    }

    private void createWindow() {
        UI ui = UI.getInstance();
        ui.getFrame().setVisible(true);
        this.setUi(ui);
    }
    private void fillData(boolean useCacheSunset) {
        this.showDateOfToday(useCacheSunset);
        this.fillSettingsForm();
    }

    private String makeLocationString(String city, String country) {
        String place = "";
        if(city.length() > 0 || country.length() > 0) {
            if(city.length() > 0) {
                place += city;
            }
            if(country.length() > 0) {
                if(place.length() > 0) {
                    place += ", ";
                }
                place += country;
            }
        }
        return place;
    }
    public Time calculateSunsetForActualLocationAndTime(boolean useCacheSunset) {
        Preferences data = this.getCurrentPreferences();
        TimeZone tz = TimeZone.getTimeZone(data.get(FIELD_TIMEZONE, DEF_TIMEZONE));
        Time time = this.calculateSunsetForActualLocation(new GregorianCalendar(tz), useCacheSunset);
        return time;
    }
    public String getIpAddress() {
        return this.getIpAddress(false);
    }
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
    public String[] getCountryAndCityFromIpAddress(String ip) {
        String[] res = new String[2];

        try {
            InputStream is = this.getClass().getResourceAsStream("/maxmind/GeoLiteCity.dat");
            File tempFile = File.createTempFile("GeoLiteCity", ".dat");
            tempFile.deleteOnExit();
            FileUtils.copyInputStreamToFile(is, tempFile);

            LookupService cl = new LookupService(tempFile ,LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
            com.maxmind.geoip.Location location = cl.getLocation(ip);
            res[0] = location.countryName;
            res[1] = location.city;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return res;
    }
    public String[] getCurrentCountryAndCity() {
        String ip = this.getIpAddress();
        String[] result = {};
        if (ip != null && !ip.isEmpty()) {
            result = this.getCountryAndCityFromIpAddress(ip);
        }
        return result;
    }
    public Time calculateSunsetForActualLocation(Calendar calendar, boolean useCacheSunset) {
        Preferences data = this.getCurrentPreferences();
        String city = data.get(FIELD_CITY, DEF_CITY);
        String country = data.get(FIELD_COUNTRY, DEF_COUNTRY);
        Time time = this.calculateSunset(calendar, city, country, useCacheSunset);
        return time;
    }
    public Time calculateSunset(Calendar calendar, String city, String country, boolean useCacheSunset) {
        return this.calculateSunset(calendar, city, country, useCacheSunset, false);
    }
    public Time calculateSunset(Calendar calendar, String city, String country, boolean useCacheSunset, boolean forceCache) {
        Time time = null;
        boolean useCache = useCacheSunset;
        Preferences data = this.getCurrentPreferences();
        if(!useCache) {
            String place = this.makeLocationString(city, country);
            if(place.length() > 0) {
                try {
                    GeoApiContext context = new GeoApiContext().setApiKey(Config.getGoogleMapsApiKey());
                    GeocodingResult[] results = GeocodingApi.geocode(context, place).await();
                    TimeZone tz = TimeZoneApi.getTimeZone(context, results[0].geometry.location).await();
                    data.put(FIELD_TIMEZONE, tz.getID());

                    double latitude = results[0].geometry.location.lat;
                    double longitude = results[0].geometry.location.lng;
                    Location location = new Location(Double.toString(latitude), Double.toString(longitude));
                    SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, tz);
                    String sunset = calculator.getOfficialSunsetForDate(calendar)+":00";
                    data.put(FIELD_CACHE_SUNSET, sunset);

                    time = Time.valueOf(sunset);
                } catch (Exception e) {
                    useCache = true;
                }
            }
            else {
                useCache = true;
            }
        }
        if(useCache && !forceCache) {
            String cacheSunset = data.get(FIELD_CACHE_SUNSET, DEF_CACHE_SUNSET);
            if(!cacheSunset.isEmpty()) {
                time = Time.valueOf(cacheSunset);
            } else {
                time = this.calculateSunset(calendar, city, country, false, true);
            }
        }
        return time;
    }
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
        if(time == null) {
            cal = new ImladrisCalendar(gcal);
        }
        else {
            cal = new ImladrisCalendar(time, gcal);
            /*check if before sunset*/
            String gtimeStr = gcal.get(GregorianCalendar.HOUR_OF_DAY)+":"+gcal.get(GregorianCalendar.MINUTE)+":"+gcal.get(GregorianCalendar.SECOND);
            Time gtime = Time.valueOf(gtimeStr);
            if(gtime.before(time)) { // before sunset
                sunsetStr = " - before sunset";
            }
            else { // after/during sunset
                sunsetStr = " - after sunset";
            }
            String sunsetTimeStr = time.toString();
            sunsetTimeStr = sunsetTimeStr.substring(0, sunsetTimeStr.length()-3);
            sunsetStr += " "+Lang.punctuation.parenthesis_open+"occurring at "+sunsetTimeStr+Lang.punctuation.parenthesis_close;
            locationInfo = this.makeLocationString(city, country);
            if(locationInfo.length() > 0) {
                locationInfo = Lang.punctuation.parenthesis_open+Lang.common.location_label+Lang.punctuation.double_dot+" "+locationInfo+" "+Lang.punctuation.pipe+" "+Lang.common.timezone_label+Lang.punctuation.double_dot+" "+data.get(FIELD_TIMEZONE, DEF_TIMEZONE)+Lang.punctuation.parenthesis_close;
            }
        }
        String gstr = this.gregorianToString(gcal);
        String istr = cal.toString();
        UI ui = this.getUi();
        ui.getTodayGregorian().setText(gstr+sunsetStr);
        ui.getTodayImladris().setText(istr);
        ui.getLocationInfo().setText(locationInfo);
    }


    /******** FROM GREGORIAN **********/

    public void updateDayComboGregorian() {
        UI window = this.getUi();

        JTextField year = window.getYear();
        JComboBox month = window.getMonth();
        JComboBox day = window.getDay();
        JButton convert = window.getToImladris();
        JTextPane result = window.getResImladris();
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
    @SuppressWarnings("deprecation")
    public void convertToImladris() {
        UI window = this.getUi();

        String errorEmptyYear = "Please insert a year.";
        String errorYearNotNumeric = "Please insert the year as a numeric value.";
        String errorYearNotValid = "Please insert a valid year (from 1 to "+Integer.toString(GregorianInfo.MAX_SUPPORTED_YEAR)+").";
        String errorDayNotRead = "Sorry, the day could not be read.";

        JTextField year = window.getYear();
        JComboBox month = window.getMonth();
        JComboBox day = window.getDay();
        JCheckBox afterSunset = window.getAfterSunset();
        JTextPane result = window.getResImladris();
        String value = year.getText();
        if(!value.isEmpty()) {
            try {
                int yearNum = Integer.parseInt(value);
                if(yearNum > 0 && yearNum <= GregorianInfo.MAX_SUPPORTED_YEAR) {
                    int monthNum = month.getSelectedIndex() + 1;
                    int dayNum = 0;
                    if(day.isEnabled()) {
                        dayNum = day.getSelectedIndex() + 1;
                        ImladrisCalendar cal;
                        if(afterSunset.isSelected()) {
                            GregorianCalendar gcal = new GregorianCalendar(yearNum, monthNum, dayNum);
                            Time time = this.calculateSunsetForActualLocation(gcal, true);
                            cal = new ImladrisCalendar(time, yearNum, monthNum, dayNum, time.getHours(), time.getMinutes(), time.getSeconds());
                        }
                        else {
                            cal = new ImladrisCalendar(yearNum, monthNum, dayNum);
                        }
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

    /************* TO GREGORIAN *************/

    public void updateDayComboImladris() {
        UI window = this.getUi();

        JComboBox yen = window.getYen();
        JTextField loa = window.getLoa();
        JComboBox period = window.getPeriod();
        JComboBox day = window.getDayOfLoa();
        JButton convert = window.getToGregorian();
        JTextPane result = window.getResGregorian();
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
    public void convertToGregorian() {
        UI window = this.getUi();

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
                        if(beforeMidnight.isSelected()) {
                            gcal.add(GregorianCalendar.DAY_OF_YEAR, -1);
                        }
                        String gstr = this.gregorianToString(gcal);
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

    /************* SETTINGS ****************/

    private void fillSettingsForm() {
        UI ui = this.getUi();
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
    }
    public void saveSettings() {
        UI ui = this.getUi();
        Preferences data = this.getCurrentPreferences();
        boolean updateDate = false;
        /* City, country, tz */
        String savedCity = data.get(FIELD_CITY, DEF_CITY);
        String newCity = ui.getCity().getText();
        if(!savedCity.equals(newCity)) {
            data.put(FIELD_CITY, newCity);
            updateDate = true;
        }
        String savedCountry = data.get(FIELD_COUNTRY, DEF_COUNTRY);
        String newCountry = ui.getCountry().getText();
        if(!savedCountry.equals(newCountry)) {
            data.put(FIELD_COUNTRY, newCountry);
            updateDate = true;
        }
        /* Lang */
        LangManager langManager = LangManager.getInstance();
        String shortLang = LangManager.getLanguagesShort()[Arrays.asList(LangManager.getLanguagesPrintable()).indexOf((String)ui.getLangCombo().getSelectedItem())];
        Lang actual = langManager.getDefinedLang();
        if(!actual.getShortName().equals(shortLang)) {
            langManager.defineLang(shortLang);
            data.put(FIELD_LANG, shortLang);
            this.reloadWindowInActualTab();
            updateDate = false;
        }
        /* Update today's date */
        if(updateDate) {
            this.showDateOfToday(false);
        }
    }

    /************ OTHER *****************/

    private String gregorianToString(GregorianCalendar gcal) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE, MMMMMMMMM d, y");
        String gstr = sdf.format(gcal.getTime());
        return gstr;
    }
    private Preferences getCurrentPreferences() {
        Preferences prefs = this.getPreferences();
        if(prefs == null || !(prefs instanceof Preferences)) {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            this.setPreferences(prefs);
        }
        return prefs;
    }


}
