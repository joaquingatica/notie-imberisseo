package com.erutulco.notieimberisseo.lang.available;

import com.erutulco.notieimberisseo.lang.Lang;

public class LangEnglish extends Lang {

  private String name = "English";
  private String arrayName = "English";
  private String shortName = "eng";

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setArrayName(String arrayName) {
    this.arrayName = arrayName;
  }

  public String getArrayName() {
    return arrayName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getShortName() {
    return shortName;
  }

  public LangEnglish() {
  }

  /**
   * Define the language's translations.
   */
  public void defineLang() {
    Lang.name = this.getName();
    Lang.short_name = this.getShortName();
    Lang.uses_tengwar = false;
    /* Punctuation */
    Punctuation.dot = ".";
    Punctuation.comma = ",";
    Punctuation.semicolon = ";";
    Punctuation.double_dot = ":";
    Punctuation.parenthesis_open = "(";
    Punctuation.parenthesis_close = ")";
    Punctuation.pipe = "|";
    /* Common */
    Common.app_title = "Notië Imberissëo";
    Common.app_version = "2.4";
    Common.app_version_date = "Lairë 70, XIV 140";
    Common.current_date_label = "Current date";
    Common.location_label = "Loc.";
    Common.timezone_label = "TZ";
    Common.gregorian_label = "Gregorian";
    Common.imladris_label = "Imladris";
    /* To Imladris tab */
    ToImladrisTab.title = "To Imladris Reckoning";
    ToImladrisTab.tooltip = "Convert from Gregorian to Imladris calendar";
    ToImladrisTab.choose_label = "Choose Gregorian date";
    ToImladrisTab.resulting_label = "Resulting Imladris date";
    ToImladrisTab.year_label = "Year";
    ToImladrisTab.year_tooltip = "Valid years: 1 - 2299";
    ToImladrisTab.month_label = "Month";
    ToImladrisTab.day_label = "Day";
    ToImladrisTab.after_sunset_label = "After sunset";
    ToImladrisTab.resulting_format = "Format: Weekday, Period [Day#], Yén Loa";
    /* From Imladris tab */
    FromImladrisTab.title = "From Imladris Reckoning";
    FromImladrisTab.tooltip = "Convert from Imladris to Gregorian calendar";
    FromImladrisTab.choose_label = "Choose Imladris date";
    FromImladrisTab.resulting_label = "Resulting Gregorian date";
    FromImladrisTab.yen_label = "Yén";
    FromImladrisTab.loa_label = "Loa";
    FromImladrisTab.loa_tooltip = "Valid loar: 1 - 144";
    FromImladrisTab.period_label = "Period";
    FromImladrisTab.day_label = "Day";
    FromImladrisTab.before_midnight_label = "Before midnight";
    FromImladrisTab.resulting_format = "Format: Weekday, Month Day, Year";
    /* Settings tab */
    SettingsTab.title = "Settings";
    SettingsTab.tooltip = "Edit configurations";
    SettingsTab.location = "Location";
    SettingsTab.city_label = "City";
    SettingsTab.country_label = "Country";
    SettingsTab.language_label = "Language";
    SettingsTab.timezone = "Timezone";
    SettingsTab.save = "Save";
    /* About tab */
    AboutTab.title = "About";
    AboutTab.tooltip = "About the application and author.";
    AboutTab.app_name = "Notië Imberissëo";
    AboutTab.tengwar_name = "5^1T`V `Bw$7T,R`H";
    AboutTab.info = "<font size=\"3\">By <b>Erutulco Eruntano</b><br />"
        + "Contact: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> or <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />"
        + "&copy; " + Common.app_version_date + "<br />"
        + "</font><br />"
        + "<font size=\"2\">Open Source Software hosted at: <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />"
        + "For reference about the <b>calendar</b>, visit Quenya101 Language Institute:<br />"
        + "<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />"
        + "<br />"
        + "Special thanks to <b><a href=\"http://twitter.com/Quenya101\">Erunno Alcarinollo</a></b>.<br />"
        + "Russian translation: <b><a href=\"http://www.facebook.com/tienoren\">Aen Oroniel Tiënoren</a></b>"
        + "</font>";
  }

}
