package com.erutulco.notieimberisseo.config;

import com.erutulco.utils.SunsetUtils;

public class Config {

  /* ***** Attributes ***** */

  private static String GOOGLE_MAPS_API_KEY = "AIzaSyAYreK5EtPQZwVr--GkX0oaSNhS-IG_x8I";
  private static long RATE_LIMIT_INTERVAL = (long) 500; // In milliseconds
  private static int CONNECTION_TIMEOUT = 3000; // In milliseconds

  /* ***** Getters & Setters ***** */

  public static Settings getSettings() {
    return Settings.getInstance();
  }

  public static String getGoogleMapsApiKey() {
    return GOOGLE_MAPS_API_KEY;
  }

  public static long getRateLimitInterval() {
    return RATE_LIMIT_INTERVAL;
  }

  public static int getConnectionTimeout() {
    return CONNECTION_TIMEOUT;
  }

  /* ***** Methods ***** */

  public static void initialize() {
    SunsetUtils.setGoogleMapsApiKey(GOOGLE_MAPS_API_KEY);
  }

  public static Settings getCurrentSettings() {
    return Config.getSettings();
  }
}
