package fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

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
	public static Font getTrueTypeFont(String fontFileName) {
		Font tengwarFont = null;
		try {
			File fontFile = new File(ClassLoader.getSystemResource("fonts/" + fontFileName).toURI());
			tengwarFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			tengwarFont = tengwarFont.deriveFont(Font.PLAIN);
			tengwarFont = tengwarFont.deriveFont((float)14);
		} catch (FontFormatException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (URISyntaxException e ){
			System.err.println(e.getMessage());
		}
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
