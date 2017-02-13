package com.erutulco.notieimberisseo.lang.available;

import com.erutulco.notieimberisseo.lang.Lang;

public class LangDeutsch extends Lang {

  private String name = "Deutsch";
  private String arrayName = "Deutsch";
  private String shortName = "deu";

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

  public LangDeutsch() {
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
    Common.current_date_label = "Aktuelle Datum";
    Common.location_label = "Lage";
    Common.timezone_label = "ZZ";
    Common.gregorian_label = "Gregorianisch";
    Common.imladris_label = "Imladris";
    /* To Imladris tab */
    ToImladrisTab.title = "Zum Imladris Kalender";
    ToImladrisTab.tooltip = "Von Gregorianisch zu Imladris Kalender verwandeln.";
    ToImladrisTab.choose_label = "Gregorianisch Datum wählen";
    ToImladrisTab.resulting_label = "Daraus resultierenden Imladris Datum";
    ToImladrisTab.year_label = "Jahr";
    ToImladrisTab.year_tooltip = "Gültige Jahre: 1 - 2299";
    ToImladrisTab.month_label = "Monat";
    ToImladrisTab.day_label = "Tag";
    ToImladrisTab.after_sunset_label = "Nach Sonnenuntergang";
    ToImladrisTab.resulting_format = "Format: Wochentag, Periode [Tag#], Yén Loa";
    /* From Imladris tab */
    FromImladrisTab.title = "Von Imladris Kalender";
    FromImladrisTab.tooltip = "Von Imladris zu Gregorianisch Kalender verwandeln.";
    FromImladrisTab.choose_label = "Imladris Datum wählen";
    FromImladrisTab.resulting_label = "Daraus resultierenden Gregorianisch Datum";
    FromImladrisTab.yen_label = "Yén";
    FromImladrisTab.loa_label = "Loa";
    FromImladrisTab.loa_tooltip = "Gültige loar: 1 - 144";
    FromImladrisTab.period_label = "Periode";
    FromImladrisTab.day_label = "Tag";
    FromImladrisTab.before_midnight_label = "Vor Mitternacht";
    FromImladrisTab.resulting_format = "Format: Wochentag, Monat Tag, Jahr";
    /* Settings tab */
    SettingsTab.title = "Einstellungen";
    SettingsTab.tooltip = "Konfigurationen bearbeiten";
    SettingsTab.location = "Lage";
    SettingsTab.city_label = "Stadt";
    SettingsTab.country_label = "Land";
    SettingsTab.language_label = "Sprache";
    SettingsTab.timezone = "Zeitzone";
    SettingsTab.save = "Speichern";
    /* About tab */
    AboutTab.title = "Informationen";
    AboutTab.tooltip = "Über die Anwendung und Autor.";
    AboutTab.app_name = "Notië Imberissëo";
    AboutTab.tengwar_name = "5^1T`V `Bw$7T,R`H";
    AboutTab.info = "<font size=\"3\">By <b>Erutulco Eruntano</b><br />"
        + "Kontakt: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> oder <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />"
        + "&copy; " + Common.app_version_date + "<br />"
        + "</font><br />"
        + "<font size=\"2\">Open-Source-Software an <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a> gehostet.<br />"
        + "Für Referenze über den <b>Kalender</b>, Quenya101 Language Institute besuchen:<br />"
        + "<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />"
        + "<br />Russisch Übersetzung"
        + "Besonderer Dank geht an <b>Erunno Alcarinollo</b>.</font>"
        + "Russisch Übersetzung: <b><a href=\"http://www.facebook.com/tienoren\">Aen Oroniel Tiënoren</a></b>";
  }

}
