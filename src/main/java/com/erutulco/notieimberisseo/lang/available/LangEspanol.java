package com.erutulco.notieimberisseo.lang.available;

import com.erutulco.notieimberisseo.lang.Lang;

public class LangEspanol extends Lang {

  private String name = "Español";
  private String arrayName = "Espanol";
  private String shortName = "esp";

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

  public LangEspanol() {
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
    Common.current_date_label = "Fecha actual";
    Common.location_label = "Ubic.";
    Common.timezone_label = "ZH";
    Common.gregorian_label = "Gregoriano";
    Common.imladris_label = "Imladris";
    /* To Imladris tab */
    ToImladrisTab.title = "A fecha de Imladris";
    ToImladrisTab.tooltip = "Convertir del calendario Gregoriano al de Imladris";
    ToImladrisTab.choose_label = "Elige fecha Gregoriana";
    ToImladrisTab.resulting_label = "Fecha de Imladris resultante";
    ToImladrisTab.year_label = "Año";
    ToImladrisTab.year_tooltip = "Años válidos: 1 - 2299";
    ToImladrisTab.month_label = "Mes";
    ToImladrisTab.day_label = "Día";
    ToImladrisTab.after_sunset_label = "Después del ocaso";
    ToImladrisTab.resulting_format = "Formato: Dia de semana, Período [#dia], Yén Loa";
    /* From Imladris tab */
    FromImladrisTab.title = "Desde fecha de Imladris";
    FromImladrisTab.tooltip = "Convertir del calendario de Imladris al Gregoriano";
    FromImladrisTab.choose_label = "Elige fecha de Imladris";
    FromImladrisTab.resulting_label = "Fecha Gregoriana resultante";
    FromImladrisTab.yen_label = "Yén";
    FromImladrisTab.loa_label = "Loa";
    FromImladrisTab.loa_tooltip = "Loar válidos: 1 - 144";
    FromImladrisTab.period_label = "Período";
    FromImladrisTab.day_label = "Día";
    FromImladrisTab.before_midnight_label = "Antes de medianoche";
    FromImladrisTab.resulting_format = "Formato: Dia de semana, Mes Día, Año";
    /* Settings tab */
    SettingsTab.title = "Configuración";
    SettingsTab.tooltip = "Editar preferencias";
    SettingsTab.location = "Ubicación";
    SettingsTab.city_label = "Ciudad";
    SettingsTab.country_label = "País";
    SettingsTab.language_label = "Idioma";
    SettingsTab.timezone = "Zona horaria";
    SettingsTab.save = "Guardar";
    /* About tab */
    AboutTab.title = "Información";
    AboutTab.tooltip = "Sobre la application y el autor";
    AboutTab.app_name = "Notië Imberissëo";
    AboutTab.tengwar_name = "5^1T`V `Bw$7T,R`H";
    AboutTab.info = "<font size=\"3\">Por <b>Erutulco Eruntano</b><br />"
        + "Contacto: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> o <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />"
        + "&copy; " + Common.app_version_date + "<br />"
        + "</font><br />"
        + "<font size=\"2\">Software Open Source alojado en: <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />"
        + "Por información sobre el <b>calendario</b>, visita Quenya101 Language Institute:<br />"
        + "<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />"
        + "<br />"
        + "Agradecimientos en especial a <b>Erunno Alcarinollo</b>.<br />"
        + "Traducción al Ruso: <b><a href=\"http://www.facebook.com/tienoren\">Aen Oroniel Tiënoren</a></b>"
        + "</font>";
  }

}