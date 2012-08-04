package lang.available;

import lang.Lang;

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
		Lang.common.current_date_label = "Aktuelle Datum";
		Lang.common.location_label = "Lage";
		Lang.common.timezone_label = "ZZ";
		Lang.common.gregorian_label = "Gregorianisch";
		Lang.common.imladris_label = "Imladris";
		/* To Imladris tab */
		Lang.to_imladris_tab.title = "Zum Imladris Kalender";
		Lang.to_imladris_tab.tooltip = "Von Gregorianisch zu Imladris Kalender verwandeln.";
		Lang.to_imladris_tab.choose_label = "Gregorianisch Datum wählen";
		Lang.to_imladris_tab.resulting_label = "Daraus resultierenden Imladris Datum";
		Lang.to_imladris_tab.year_label = "Jahr";
		Lang.to_imladris_tab.year_tooltip = "Gültige Jahre: 1 - 2299";
		Lang.to_imladris_tab.month_label = "Monat";
		Lang.to_imladris_tab.day_label = "Tag";
		Lang.to_imladris_tab.after_sunset_label = "Nach Sonnenuntergang";
		Lang.to_imladris_tab.resulting_format = "Format: Wochentag, Periode [Tag#], Yén Loa";
		/* From Imladris tab */
		Lang.from_imladris_tab.title = "Von Imladris Kalender";
		Lang.from_imladris_tab.tooltip = "Von Imladris zu Gregorianisch Kalender verwandeln.";
		Lang.from_imladris_tab.choose_label = "Imladris Datum wählen";
		Lang.from_imladris_tab.resulting_label = "Daraus resultierenden Gregorianisch Datum";
		Lang.from_imladris_tab.yen_label = "Yén";
		Lang.from_imladris_tab.loa_label = "Loa";
		Lang.from_imladris_tab.loa_tooltip = "Gültige loar: 1 - 144";
		Lang.from_imladris_tab.period_label = "Periode";
		Lang.from_imladris_tab.day_label = "Tag";
		Lang.from_imladris_tab.before_midnight_label = "Vor Mitternacht";
		Lang.from_imladris_tab.resulting_format = "Format: Wochentag, Monat Tag, Jahr";
		/* Settings tab */
		Lang.settings_tab.title = "Einstellungen";
		Lang.settings_tab.tooltip = "Konfigurationen bearbeiten";
		Lang.settings_tab.location = "Lage";
		Lang.settings_tab.city_label = "Stadt";
		Lang.settings_tab.country_label = "Land";
		Lang.settings_tab.language_label = "Sprache";
		Lang.settings_tab.timezone = "Zeitzone";
		Lang.settings_tab.save = "Speichern";
		/* About tab */
		Lang.about_tab.title = "Informationen";
		Lang.about_tab.tooltip = "Über die Anwendung und Autor.";
		Lang.about_tab.app_name = "Notië Imberissëo";
		Lang.about_tab.tengwar_name = "5^1T`V `Bw$7T,R`H";
		Lang.about_tab.info = "<font size=\"3\">By <b>Erutulco Eruntano</b><br />" +
							  "Kontakt: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> oder <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />" +
							  "&copy; "+Lang.common.app_version_date+"<br />" +
					  		  "</font><br />" +
						  	  "<font size=\"2\">Open-Source-Software an <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a> gehostet.<br />" +
						  	  "Für Referenze über den <b>Kalender</b>, Quenya101 Language Institute besuchen:<br />" +
						  	  "<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />" +
						  	  "<br />" +
						  	  "Besonderer Dank geht an <b>Erunno Alcarinollo</b>.</font>";
	}
	
}
