package lang.available;

import lang.Lang;

public class LangQuenya extends Lang {

  private String name = "Quenya";
  private String arrayName = "Quenya";
  private String shortName = "qya";

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

  public LangQuenya() {
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
    Common.current_date_label = "Ré ar lúme sírava"; // day and hour of today
    Common.location_label = "Nómë"; // place
    Common.timezone_label = "Lúme arda"; // time realm/region
    Common.gregorian_label = "Tirinquova"; // Gregorian
    Common.imladris_label = "Imberissëo"; // of Imladris
    /* To Imladris tab */
    ToImladrisTab.title = "Notienna Imberissëo"; // To Imladris Reckoning
    // to turn times from Reckoning of Gregory to Reckoning of Imladris
    ToImladrisTab.tooltip = "Querien lúmi Notiello Tirinquova Notienna Imberissëo";
    ToImladrisTab.choose_label = "Á mitta lúmë Tirinquova"; // insert Gregorian time
    ToImladrisTab.resulting_label = "Imberissëo lúmë"; // time of Imladris
    ToImladrisTab.year_label = "Loa"; // year
    // Tested years: 1 - 13B7 (In decimal system: 2299)
    ToImladrisTab.year_tooltip = "Tyastainar loar: 1 - 13B7 (Manquanotiessë: 2299)";
    ToImladrisTab.month_label = "Asta"; // month/part
    ToImladrisTab.day_label = "Ré"; // day
    ToImladrisTab.after_sunset_label = "Apa i andúnë"; // after sunset
    // Shape: Day of week, period [day number], yen loa
    ToImladrisTab.resulting_format = "Venwë: Ré equiéva, Asta [Ré nóte], Yén Loa";
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
    AboutTab.app_name = "Notië Imberissëo";
    AboutTab.tengwar_name = "5^1T`V `Bw$7T,R`H";
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