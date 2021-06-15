package boulderDash.grafica;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class Boton extends JButton {
	
	private static final long serialVersionUID = 1L;

	private void estiloBasico() {
		
		// Configura el estilo. Setea el tama�o, borra margenes, bordes, fondo
		// y alinea la oracion a la izquierda
		setForeground(Color.WHITE);
		setMargin(null);
		setBorder(null);
		setOpaque(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
	}
	
	public Boton(String str, int x, int y) {
		this(str, x, y, 480, 30);
	}
	
	public Boton(String str, int x, int y, int width, int height) {
		super(str);
		setBounds(x, y, width, height);
		
		estiloBasico();
		
		setFont(AdminFont.getFont(20f));
		
		addMouseListener(new MouseAdapter( ) {
			public void mouseEntered(MouseEvent arg0) {
				cambiarColor(Color.YELLOW);
			}
			
			public void mouseExited(MouseEvent arg0) {
				cambiarColor(Color.WHITE);
			}
		});
	}
	
	public Boton(Icon icon, int x, int y, int width, int height) {
		super(icon);
		
		estiloBasico();
		
		setBounds(x, y, width, height);
	}
	
	public void cambiarColor(Color color) {
		this.setForeground(color);
	}
}
