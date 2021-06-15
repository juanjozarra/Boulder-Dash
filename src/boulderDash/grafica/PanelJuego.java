package boulderDash.grafica;

import java.awt.Color;

public class PanelJuego extends PanelPrincipal {
	
	private static final long serialVersionUID = 1L;
	private Tablero tablero;
	private Header header;
	
	public PanelJuego() {
		super();
		setBackground(Color.BLACK);
		
		tablero = new Tablero();
		tablero.setBounds(0, 48, 960, 528);
		add(tablero);
		
		header = new Header();
		header.setBounds(0, 0, 960, 48);
		add(header);
	}
	
	/**
	 * Carga todas las imagenes a escondidadas para que despues no haya
	 * pantallasos
	 * 
	 */
	public void cachearImagenes() {
		tablero.cachearImagenes();
	}
}
