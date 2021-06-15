package boulderDash.grafica;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import boulderDash.InfoNivel;
import boulderDash.Juego;
import boulderDash.complementos.ImageNotFoundException;
import boulderDash.controlador.TipoEntidad;
import boulderDash.entidades.Rockford;

public class Header extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private LabelJuego nivel;
	private LabelJuego diamantes;
	private LabelJuego valorDiamantes;
	private LabelJuego tiempo;
	private LabelJuego score;
	private Vidas vidas;
	
	
	public Header() {
		setLayout(null);
		setBorder(null);

		nivel = new LabelJuego("??", 6, 6, 140, 36);
		add(nivel);
		
		diamantes = new LabelJuego("??/??", 152, 6, 145, 36);
		try {
			diamantes.setIcon(AdminImagen.getInstance().getImageIcon(TipoEntidad.DIAMANTE));
		} catch (ImageNotFoundException e) {
			diamantes.setIcon(AdminImagen.getInstance().getErrorImageIcon());
		}
		diamantes.setIconTextGap(8);
		add(diamantes);
	
		valorDiamantes = new LabelJuego("??", 306, 6, 60, 36);
		add(valorDiamantes);
	
		tiempo = new LabelJuego("???", 303 + 72, 6, 110, 36);
		tiempo.setIcon(new ImageIcon(getClass().getResource("img/reloj.jpg")));
		tiempo.setIconTextGap(8);
		add(tiempo);
		
		score = new LabelJuego("SCORE:????/????", 419 + 72, 6, 300, 36);
		add(score);
		
		vidas = new Vidas(810, 6);
		add(vidas);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Referencia a juego para ahorrar tiempo y sintaxis
		Juego juego = Juego.getInstance();
		
		// Setea el fondo de nuevo para evitar errores
		setBackground(Color.BLACK);
		
		// Le pide inforamcion varia al juego
		InfoNivel info = juego.getInfoNivel();
		int diamantesRockford = Rockford.getInstance().getDiamantes();
		
		nivel.setText(String.format("LVL:%02d", info.getNivel()));
		
		if (diamantesRockford >= info.getDiamanteMin())
			diamantes.setForeground(Color.GREEN);
		else
			diamantes.setForeground(Color.WHITE);
		diamantes.setText(String.format("%02d/%02d", diamantesRockford, info.getDiamanteMin()));
		
		valorDiamantes.setText(String.format("%02d", juego.getValorDiamante()));
		
		if (juego.pausado())
			tiempo.setForeground(Color.RED);
		else
			tiempo.setForeground(Color.WHITE);
		tiempo.setText(String.format("%03d", Juego.getInstance().getTiempo()));
		
		score.setText(String.format("SCORE:%04d/%04d", juego.getPuntuacionGeneral(), juego.getPuntuacionNivel()));
		
		vidas.setVidas(Rockford.getInstance().getVidas());
	}
}
