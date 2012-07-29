package fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
	
	private static final String[] fonts = {"tngan.ttf", "tnganb.ttf", "tngani.ttf"};
	
	public static Font getFont(String fontFileName, String altFontName, int style, int size) {
		Font font = null;
		try {
			font = FontManager.getTrueTypeFont(fontFileName);
			font = font.deriveFont(style);
			font = font.deriveFont((float)size);
		} catch (Exception e) {
			font = new Font(altFontName, style, size);
		}
		return font;
	}
	public static Font getTrueTypeFont(String fontFileName) throws FontFormatException, IOException {
		InputStream fontStream = new Object().getClass().getResourceAsStream("/fonts/files/"+fontFileName);
		Font tengwarFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
		return tengwarFont;
	}
	public static boolean registerFont(String fontFilename) {
		boolean success;
		try {
			Font font = FontManager.getTrueTypeFont(fontFilename);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
	public static void registerAvailableFonts() {
		for(int i = 0; i < FontManager.fonts.length; i++) {
			FontManager.registerFont(fonts[i]);
		}
	}
	
}
