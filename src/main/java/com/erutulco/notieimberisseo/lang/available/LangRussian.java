package com.erutulco.notieimberisseo.lang.available;

import com.erutulco.notieimberisseo.lang.Lang;

public class LangRussian extends Lang {

  private String name = "русский";
  private String arrayName = "Russian";
  private String shortName = "rus";

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

  public LangRussian() {
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
    Common.current_date_label = "Текущая дата";
    Common.location_label = "Мест.";
    Common.timezone_label = "Часовой пояс";
    Common.gregorian_label = "Григорианский";
    Common.imladris_label = "Имладрис";
    /* To Imladris tab */
    ToImladrisTab.title = "В летоисчисление Имладриса";
    ToImladrisTab.tooltip = "Перевести григорианскую дату в календарь Имладриса";
    ToImladrisTab.choose_label = "Выбрать григорианскую дату";
    ToImladrisTab.resulting_label = "Вычисление даты Имладриса";
    ToImladrisTab.year_label = "Год";
    ToImladrisTab.year_tooltip = "Допустимые года: 1 — 2299";
    ToImladrisTab.month_label = "Месяц";
    ToImladrisTab.day_label = "День";
    ToImladrisTab.after_sunset_label = "После заката";
    ToImladrisTab.resulting_format = "Формат: день недели, период [число], Yén Loa";
    /* From Imladris tab */
    FromImladrisTab.title = "Из летоисчисления Имладриса";
    FromImladrisTab.tooltip = "Перевести из календаря Имладриса в григорианскую дату";
    FromImladrisTab.choose_label = "Выбрать дату по календарю Имладриса";
    FromImladrisTab.resulting_label = "Вычисление григорианской даты";
    FromImladrisTab.yen_label = "Yén";
    FromImladrisTab.loa_label = "Loa";
    FromImladrisTab.loa_tooltip = "Допустимые loar: 1 — 144";
    FromImladrisTab.period_label = "Период";
    FromImladrisTab.day_label = "День";
    FromImladrisTab.before_midnight_label = "До полуночи";
    FromImladrisTab.resulting_format = "Формат: день недели, число, год";
    /* Settings tab */
    SettingsTab.title = "Настройки";
    SettingsTab.tooltip = "Редактировать конфигурации";
    SettingsTab.location = "Местонахождение";
    SettingsTab.city_label = "Город";
    SettingsTab.country_label = "Страна";
    SettingsTab.language_label = "Язык";
    SettingsTab.timezone = "Часовой пояс";
    SettingsTab.save = "Сохранить";
    /* About tab */
    AboutTab.title = "О программе";
    AboutTab.tooltip = "О программе и её авторе";
    AboutTab.app_name = "Notië Imberissëo";
    AboutTab.tengwar_name = "5^1T`V `Bw$7T,R`H";
    AboutTab.info = "<font size=\"3\">Автор: <b>Эрутулко Эрунтано</b><br />"
        + "Контакты: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> или <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />"
        + "&copy; " + Common.app_version_date + "<br />"
        + "</font><br />"
        + "<font size=\"2\">Программа с открытыми исходными кодами: <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />"
        + "Для справки о календаре посетите Quenya101 Language Institute:<br />"
        + "<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />"
        + "<br />"
        + "Особая благодарность <b>Эрунно Алькаринолло</b>.<br />"
        + "Перевод на русский: <b><a href=\"http://www.facebook.com/tienoren\">Аэн Орониэль Тиэнорен</a></b></font>";

  }

}
