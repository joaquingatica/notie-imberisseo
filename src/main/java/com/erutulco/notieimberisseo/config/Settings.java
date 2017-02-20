package com.erutulco.notieimberisseo.config;

import java.util.HashMap;
import java.util.TimeZone;
import java.util.prefs.Preferences;

public class Settings {

  /* ****** Constants ****** */

  private static final String[] FIELDS = {
      "city", "country", "language", "timezone", "sunset", "ip"
  };
  private static String[] DEFAULTS = {
      "Uruguay", "Montevideo", "eng", TimeZone.getDefault().getID(), "", ""
  };
  public static final int CITY = 0;
  public static final int COUNTRY = 1;
  public static final int LANG = 2;
  public static final int TIMEZONE = 3;
  public static final int SUNSET = 4;
  public static final int IP = 5;

  /* ****** Attributes ****** */

  private Preferences preferences = null;
  private final HashMap<String, String> map = new HashMap<>();

  /* ****** Singleton ****** */

  private static Settings instance = null;

  /**
   * Singleton method.
   * @return Singleton instance of Settings
   */
  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }

  private Settings() {
    // set initial value of all fields to default
    for (int i = 0; i < FIELDS.length; i++) {
      this.set(i, DEFAULTS[i]);
    }
  }

  /* ****** API ****** */
  private Preferences getPreferences() {
    if (preferences == null) {
      preferences = Preferences.userRoot().node(Settings.class.getName());
    }
    return preferences;
  }

  public void set(int field, String value) {
    this.getPreferences().put(FIELDS[field], value);
  }

  public String get(int field) {
    return this.get(FIELDS[field], DEFAULTS[field]);
  }

  private String get(String key, String def) {
    return this.getPreferences().get(key, def);
  }


  public void setDefault(int field, String value) {
    DEFAULTS[field] = value;
  }

  public String getDefault(int field) {
    return DEFAULTS[field];
  }
}
