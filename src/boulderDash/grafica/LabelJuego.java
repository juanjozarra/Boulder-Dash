package boulderDash.grafica;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelJuego extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int BORDER_WIDTH = 3;
	
	public LabelJuego(String str, int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setText(str);
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.WHITE/*new Color(41,129,255)*/, BORDER_WIDTH));
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		
		setFont(AdminFont.getFont(18.5F));
		
	}
}
