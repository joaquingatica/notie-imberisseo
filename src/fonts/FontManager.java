package fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
	
	public static Font getTrueTypeFont(String fontFileName) throws FontFormatException, IOException {
		InputStream fontStream = new Object().getClass().getResourceAsStream("/fonts/files/"+fontFileName);
		Font tengwarFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
		return tengwarFont;
	}
	
}
