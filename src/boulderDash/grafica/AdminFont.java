package boulderDash.grafica;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import boulderDash.controlador.Controlador;

public class AdminFont {

	private static Font font = null;
	
	public static Font getFont(float f) {
		// TODO Mejorar el tratamiento de errores
		if (font == null) {
			// Trata de cargar la fuente y obtener el tamaño solicitado
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, Grafica.class.getResourceAsStream("font/PressStart2P.ttf"));
			} catch (IOException e) {
				System.out.println(new File("algo") .getAbsolutePath());
				e.printStackTrace();
				Controlador.kill();
			} catch (FontFormatException e) {
				e.printStackTrace();
				Controlador.kill();
			}
		}
		
		font = font.deriveFont(f);
		return font;
	}
	
}
