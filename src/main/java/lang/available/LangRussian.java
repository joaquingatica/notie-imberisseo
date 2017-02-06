package lang.available;

import lang.Lang;

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

    @Override
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
        Lang.common.current_date_label = "Текущая дата";
        Lang.common.location_label = "Мест.";
        Lang.common.timezone_label = "Часовой пояс";
        Lang.common.gregorian_label = "Григорианский";
        Lang.common.imladris_label = "Имладрис";
        /* To Imladris tab */
        Lang.to_imladris_tab.title = "В летоисчисление Имладриса";
        Lang.to_imladris_tab.tooltip = "Перевести григорианскую дату в календарь Имладриса";
        Lang.to_imladris_tab.choose_label = "Выбрать григорианскую дату";
        Lang.to_imladris_tab.resulting_label = "Вычисление даты Имладриса";
        Lang.to_imladris_tab.year_label = "Год";
        Lang.to_imladris_tab.year_tooltip = "Допустимые года: 1 — 2299";
        Lang.to_imladris_tab.month_label = "Месяц";
        Lang.to_imladris_tab.day_label = "День";
        Lang.to_imladris_tab.after_sunset_label = "После заката";
        Lang.to_imladris_tab.resulting_format = "Формат: день недели, период [число], Yén Loa";
        /* From Imladris tab */
        Lang.from_imladris_tab.title = "Из летоисчисления Имладриса";
        Lang.from_imladris_tab.tooltip = "Перевести из календаря Имладриса в григорианскую дату";
        Lang.from_imladris_tab.choose_label = "Выбрать дату по календарю Имладриса";
        Lang.from_imladris_tab.resulting_label = "Вычисление григорианской даты";
        Lang.from_imladris_tab.yen_label = "Yén";
        Lang.from_imladris_tab.loa_label = "Loa";
        Lang.from_imladris_tab.loa_tooltip = "Допустимые loar: 1 — 144";
        Lang.from_imladris_tab.period_label = "Период";
        Lang.from_imladris_tab.day_label = "День";
        Lang.from_imladris_tab.before_midnight_label = "До полуночи";
        Lang.from_imladris_tab.resulting_format = "Формат: день недели, число, год";
        /* Settings tab */
        Lang.settings_tab.title = "Настройки";
        Lang.settings_tab.tooltip = "Редактировать конфигурации";
        Lang.settings_tab.location = "Местонахождение";
        Lang.settings_tab.city_label = "Город";
        Lang.settings_tab.country_label = "Страна";
        Lang.settings_tab.language_label = "Язык";
        Lang.settings_tab.timezone = "Часовой пояс";
        Lang.settings_tab.save = "Сохранить";
        /* About tab */
        Lang.about_tab.title = "О программе";
        Lang.about_tab.tooltip = "О программе и её авторе";
        Lang.about_tab.app_name = "Notië Imberissëo";
        Lang.about_tab.tengwar_name = "5^1T`V `Bw$7T,R`H";
        Lang.about_tab.info = "<font size=\"3\">Автор: <b>Эрутулко Эрунтано</b><br />"
                +"Контакты: <a href=\"http://twitter.com/joaquingatica\">@joaquingatica</a> или <a href=\"mailto:erutulco@quenya101.com\">erutulco@quenya101.com</a><br />"
                +"&copy; "+Lang.common.app_version_date+"<br />"
                +"</font><br />"
                +"<font size=\"2\">Программа с открытыми исходными кодами: <a href=\"http://github.com/joaquingatica/Notie-Imberisseo\">github.com/joaquingatica/Notie-Imberisseo</a><br />"
                +"Для справки о календаре посетите Quenya101 Language Institute:<br />"
                +"<a href=\"http://www.quenya101.com\">www.quenya101.com</a><br />"
                +"<br />"
                +"Особая благодарность <b>Эрунно Алькаринолло</b>.<br />"
                +"Перевод на русский: <b><a href=\"http://www.facebook.com/tienoren\">Аэн Орониэль Тиэнорен</a></b></font>";

    }

}
