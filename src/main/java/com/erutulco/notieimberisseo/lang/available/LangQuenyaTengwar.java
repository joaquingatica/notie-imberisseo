package com.erutulco.notieimberisseo.lang.available;

import com.erutulco.notieimberisseo.lang.Lang;

public class LangQuenyaTengwar extends Lang {

  private String name = "Quenya Tengwar";
  private String arrayName = "QuenyaTengwar";
  private String shortName = "qyt";

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

  public LangQuenyaTengwar() {
  }

  /**
   * Define the language's translations.
   */
  public void defineLang() {
    Lang.name = this.getName();
    Lang.short_name = this.getShortName();
    Lang.uses_tengwar = true;
    /* Punctuation */
    Punctuation.dot = "-";
    Punctuation.comma = "=";
    Punctuation.semicolon = "=";
    Punctuation.double_dot = "-";
    Punctuation.parenthesis_open = "›";
    Punctuation.parenthesis_close = "›";
    Punctuation.pipe = "ˆ";
    /* Common */
    Common.app_title = "Notië Imberissëo";
    Common.app_version = "ò-ô";
    Common.app_version_date = "jlE7R õú, òñ øû";
    Common.current_date_label = "7~V `C6 j~Mt$ 8~B7EyE"; // day and hour of today
    Common.location_label = "5~Nt$"; // place
    Common.timezone_label = "j~Mt$ `CuE"; // time realm/region
    Common.gregorian_label = "1T7Tv^yE"; // Gregorian
    Common.imladris_label = "`Bw$7T,R`H"; // of Imladris
    /* To Imladris tab */
    ToImladrisTab.title = "5^1T`V5\"# `Bw$7T,R`H"; // To Imladris Reckoning
    // to turn times from Reckoning of Gregory to Reckoning of Imladris
    ToImladrisTab.tooltip = "zR7T`V5 j~Mt% 5^1T`Vj¸Y 1T7Tv^yE 5^1T`V5\"# `Bw$7T,R`N";
    ToImladrisTab.choose_label = "~C t%1'E j~Mt$ 1T7Tv^yE"; // insert Gregorian time
    ToImladrisTab.resulting_label = "`Bw$7T,R`H j~Mt$"; // time of Imladris
    ToImladrisTab.year_label = "jY`C"; // year
    // Tested years: 1 - 13B7 (In decimal system: 2299)
    ToImladrisTab.year_tooltip = "1ÍE81lE5#6 jY`C6- ñ Â ñóû÷ ›t#v#5^1T`V,R- ò-ò-ù-ù›";
    ToImladrisTab.month_label = "`C81E"; // month/part
    ToImladrisTab.day_label = "7~V"; // day
    ToImladrisTab.after_sunset_label = "`CqE `B `C2~M5$"; // after sunset
    // Shape: Day of week, period [day number], yen loa
    ToImladrisTab.resulting_format = "yR5nR- 7~V `VzT~VyE= `C81E ›7~V 5~N1R›= hÍ~V5 jY`C";
    /* From Imladris tab */
    FromImladrisTab.title = "Notiello Imberissëo"; // From Imladris Reckoning
    // to turn times from Reckoning of Imladris to Reckoning of Gregory";
    FromImladrisTab.tooltip = "Querien lúmi Notiello Imberissëo Notienna Tirinquova ";
    FromImladrisTab.choose_label = "Á mitta Imberissëo lúmë"; // insert time of Imladris
    FromImladrisTab.resulting_label = "Lúmë Tirinquova"; // Gregorian time
    FromImladrisTab.yen_label = "Yén"; // yén
    FromImladrisTab.loa_label = "Loa"; // loa
    // Tested loar: 1 - 100 (In decimal system: 144)
    FromImladrisTab.loa_tooltip = "Tyastainar loar: 1 - 100 (Manquanotiessë: 144)";
    FromImladrisTab.period_label = "Asta"; // period
    FromImladrisTab.day_label = "Ré"; // day
    FromImladrisTab.before_midnight_label = "Nó i endë i lómiva"; // before middle of night
    // Shape: Day of week, period day, year
    FromImladrisTab.resulting_format = "Venwë: Ré equiéva, Asta Ré, Loa";
    /* Settings tab */
    SettingsTab.title = "Panier"; // (gerund) settings
    SettingsTab.tooltip = "Tulcien panier"; // to establish/set settings
    SettingsTab.location = "Nómë"; // place
    SettingsTab.city_label = "Osto"; // city
    SettingsTab.country_label = "Nórë"; // country/land
    SettingsTab.language_label = "Lambë"; // tongue/language
    SettingsTab.timezone = "Lúme arda"; // time realm/region
    SettingsTab.save = "Á carë rehtië"; // do saving
    /* About tab */
    AboutTab.title = "Sino"; // about this
    AboutTab.tooltip = "Sino ar nio."; // about this and about me
    AboutTab.app_name = "5^1T`V `Bw$7T,R`H";
    AboutTab.tengwar_name = "";
    AboutTab.info = "<font size=\"3\">Ló <b>Erutulco Eruntano</b><br />" // By Joaquín son of John
        + "Manen hiritan: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> hya <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />" // how to find me: ... or ...
        + "&copy; " + Common.app_version_date + "<br />"
        + "</font><br />"
        + "<font size=\"2\"><a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />"
        + "Amban i <b>notiéo</b>, á lelya ana <a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />" // for more about the reckoning, go to
        + "<br />"
        + "Hantanyë <b>Erunno Alcarinollo</b>.</font>"; // I thank Erunno Alcarinollo
  }

}
