package boulderDash.grafica.menus;

import java.awt.Color;

import boulderDash.grafica.PanelPrincipal;

public class MenuPrincipal extends PanelPrincipal {
	
	private static final long serialVersionUID = 1L;
	private int numTabla = 5;
	
	public MenuPrincipal() {
		super();
		this.setOpaque(true);
		
		this.setSize(960, 576);
		
		setBackground(new Color(65, 65, 254));
		mostrarInicio();
	}
	
	public void mostrarInicio() {
		removeAll();
		add(new Inicio(this));
		repaint();
		
	}

	/**
	 * Muestra la tabla de puntuacion
	 * 
	 */
	public void mostrarPuntuacion() {
		removeAll();
		add(new TablaPuntuacion(this, numTabla));
		repaint();
	}
	
	public void mostrarInformacion() {
		removeAll();
		add(new Informacion(this));
		repaint();
	}

	public void ingresarPuntuacion() {
		removeAll();
		add(new IngresarPuntuacion(this));
		repaint();
	}
	
	public void mostrarConfig() {
		removeAll();
		add(new Configuracion(this));
		repaint();
	}
	
	public void setNumTabla(Integer i) {
		numTabla = i;
	}

}
