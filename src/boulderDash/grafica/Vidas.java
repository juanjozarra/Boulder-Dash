package boulderDash.grafica;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Vidas extends JLabel {

	private static final long serialVersionUID = 1L;
	private int vidas = 4;
	private ImageIcon corazon;
	
	public Vidas(int x, int y) {
		setBounds(x, y, 144, 36);
		corazon = new ImageIcon(getClass().getResource("img/hearth.png"));
	}
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < vidas; i++) {
			g.drawImage(corazon.getImage(), 108 - 36*i, 2, 30, 30, null);
		}
	}
}
