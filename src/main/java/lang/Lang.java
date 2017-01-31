package lang;

public abstract class Lang {
	
	public static String name;
	public static String short_name;
	public static boolean uses_tengwar;

	public abstract String getName();
	public abstract String getArrayName();
	public abstract String getShortName();
	public abstract void defineLang();
	
	public static class punctuation {
		public static String dot;
		public static String comma;
		public static String semicolon;
		public static String double_dot;
		public static String parenthesis_open;
		public static String parenthesis_close;
		public static String pipe;
	}
	public static class common {
		public static String app_title;
		public static String app_version;
		public static String app_version_date;
		public static String current_date_label;
		public static String location_label;
		public static String timezone_label;
		public static String gregorian_label;
		public static String imladris_label;
	}
	public static class to_imladris_tab {
		public static String title;
		public static String tooltip;
		public static String choose_label;
		public static String resulting_label;
		public static String year_label;
		public static String year_tooltip;
		public static String month_label;
		public static String day_label;
		public static String after_sunset_label;
		public static String resulting_format;
	}
	public static class from_imladris_tab {
		public static String title;
		public static String tooltip;
		public static String choose_label;
		public static String resulting_label;
		public static String yen_label;
		public static String loa_label;
		public static String loa_tooltip;
		public static String period_label;
		public static String day_label;
		public static String before_midnight_label;
		public static String resulting_format;
	}
	public static class settings_tab {
		public static String title;
		public static String tooltip;
		public static String location;
		public static String city_label;
		public static String country_label;
		public static String language_label;
		public static String timezone;
		public static String save;
	}
	public static class about_tab {
		public static String title;
		public static String tooltip;
		public static String app_name;
		public static String tengwar_name;
		public static String info;
	}
	
}
