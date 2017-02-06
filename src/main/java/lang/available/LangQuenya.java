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

    public void defineLang() {
        Lang.name = this.getName();
        Lang.short_name = this.getShortName();
        Lang.uses_tengwar = false;
        /* Punctuation */
        Lang.punctuation.dot = ".";
        Lang.punctuation.comma = ",";
        Lang.punctuation.semicolon = ";";
        Lang.punctuation.double_dot = ":";
        Lang.punctuation.parenthesis_open = "(";
        Lang.punctuation.parenthesis_close = ")";
        Lang.punctuation.pipe = "|";
        /* Common */
        Lang.common.app_title = "Notië Imberissëo";
        Lang.common.app_version = "2.4";
        Lang.common.app_version_date = "Lairë 70, XIV 140";
        Lang.common.current_date_label = "Ré ar lúme sírava"; // day and hour of today
        Lang.common.location_label = "Nómë"; // place
        Lang.common.timezone_label = "Lúme arda"; // time realm/region
        Lang.common.gregorian_label = "Tirinquova"; // Gregorian
        Lang.common.imladris_label = "Imberissëo"; // of Imladris
        /* To Imladris tab */
        Lang.to_imladris_tab.title = "Notienna Imberissëo"; // To Imladris Reckoning
        Lang.to_imladris_tab.tooltip = "Querien lúmi Notiello Tirinquova Notienna Imberissëo"; // to turn times from Reckoning of Gregory to Reckoning of Imladris
        Lang.to_imladris_tab.choose_label = "Á mitta lúmë Tirinquova"; // insert Gregorian time
        Lang.to_imladris_tab.resulting_label = "Imberissëo lúmë"; // time of Imladris
        Lang.to_imladris_tab.year_label = "Loa"; // year
        Lang.to_imladris_tab.year_tooltip = "Tyastainar loar: 1 - 13B7 (Manquanotiessë: 2299)"; // Tested years: 1 - 13B7 (In decimal system: 2299)
        Lang.to_imladris_tab.month_label = "Asta"; // month/part
        Lang.to_imladris_tab.day_label = "Ré"; // day
        Lang.to_imladris_tab.after_sunset_label = "Apa i andúnë"; // after sunset
        Lang.to_imladris_tab.resulting_format = "Venwë: Ré equiéva, Asta [Ré nóte], Yén Loa"; // Shape: Day of week, period [day number], yen loa
        /* From Imladris tab */
        Lang.from_imladris_tab.title = "Notiello Imberissëo"; // From Imladris Reckoning
        Lang.from_imladris_tab.tooltip = "Querien lúmi Notiello Imberissëo Notienna Tirinquova ";// to turn times from Reckoning of Imladris to Reckoning of Gregory";
        Lang.from_imladris_tab.choose_label = "Á mitta Imberissëo lúmë"; // insert time of Imladris
        Lang.from_imladris_tab.resulting_label = "Lúmë Tirinquova"; // Gregorian time
        Lang.from_imladris_tab.yen_label = "Yén"; // yén
        Lang.from_imladris_tab.loa_label = "Loa"; // loa
        Lang.from_imladris_tab.loa_tooltip = "Tyastainar loar: 1 - 100 (Manquanotiessë: 144)"; // Tested loar: 1 - 100 (In decimal system: 144)
        Lang.from_imladris_tab.period_label = "Asta"; // period
        Lang.from_imladris_tab.day_label = "Ré"; // day
        Lang.from_imladris_tab.before_midnight_label = "Nó i endë i lómiva"; // before middle of night
        Lang.from_imladris_tab.resulting_format = "Venwë: Ré equiéva, Asta Ré, Loa"; // Shape: Day of week, period day, year
        /* Settings tab */
        Lang.settings_tab.title = "Panier"; // (gerund) settings
        Lang.settings_tab.tooltip = "Tulcien panier"; // to establish/set settings
        Lang.settings_tab.location = "Nómë"; // place
        Lang.settings_tab.city_label = "Osto"; // city
        Lang.settings_tab.country_label = "Nórë"; // country/land
        Lang.settings_tab.language_label = "Lambë"; // tongue/language
        Lang.settings_tab.timezone = "Lúme arda"; // time realm/region
        Lang.settings_tab.save = "Á carë rehtië"; // do saving
        /* About tab */
        Lang.about_tab.title = "Sino"; // about this
        Lang.about_tab.tooltip = "Sino ar nio."; // about this and about me
        Lang.about_tab.app_name = "Notië Imberissëo";
        Lang.about_tab.tengwar_name = "5^1T`V `Bw$7T,R`H";
        Lang.about_tab.info = "<font size=\"3\">Ló <b>Erutulco Eruntano</b><br />" + // By Joaquín son of John
                  "Manen hiritan: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> hya <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />" + // how to find me: ... or ...
                  "&copy; "+Lang.common.app_version_date+"<br />" +
                  "</font><br />" +
                  "<font size=\"2\"><a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />" +
                  "Amban i <b>notiéo</b>, á lelya ana <a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />" + // for more about the reckoning, go to
                  "<br />" +
                  "Hantanyë <b>Erunno Alcarinollo</b>.</font>"; // I thank Erunno Alcarinollo
}

}