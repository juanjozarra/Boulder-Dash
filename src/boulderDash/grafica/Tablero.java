package boulderDash.grafica;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import boulderDash.Juego;
import boulderDash.complementos.Direccion;
import boulderDash.complementos.ImageNotFoundException;
import boulderDash.controlador.Controlador;
import boulderDash.controlador.TipoEntidad;
import boulderDash.entidades.Rockford;

public class Tablero extends JPanel {
	
	private static final long serialVersionUID = 1L;


	public Tablero() {
		// TODO cargar los sprites en statics
		this.setLayout(new GridLayout(22, 40));
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				Direccion mov = Direccion.NULA;
				switch (key) {
				case (KeyEvent.VK_UP):
					mov = Direccion.ARRIBA;
					break;
				case (KeyEvent.VK_DOWN):
					mov = Direccion.ABAJO;
					break;
				case (KeyEvent.VK_LEFT):
					mov = Direccion.IZQUIERDA;
					break;
				case (KeyEvent.VK_RIGHT):
					mov = Direccion.DERECHA;
					break;
				case (KeyEvent.VK_ESCAPE):
					Controlador.gameOver();
					break;
					
				case (KeyEvent.VK_P):
					Juego.getInstance().togglePausa();
					break;
					
				case (KeyEvent.VK_R):
					Rockford.getInstance().kill();
					break;
				}				
				
				Controlador.setTeclaPresionada(mov);
				Controlador.setSiguienteMovimiento(mov);
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				Controlador.setTeclaPresionada(Direccion.NULA);
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		TipoEntidad[][] mapa = Juego.getInstance().getMapa();
		 
		for (int y = 0; y < mapa[0].length; y++) {
			for (int x = 0; x < mapa.length; x++) {
				Image imgAux;
				try {
					imgAux = AdminImagen.getInstance().getImage(mapa[x][y]);
				} catch (ImageNotFoundException e) {
					imgAux = AdminImagen.getInstance().getErrorImage();
				}
				g.drawImage(imgAux, x*24, y*24, null);
			}
		}

		requestFocus();
	}
	
	
	/**
	 * Carga todas las imagenes del juego antes de arrancar para asegurarse
	 * de que despues no aparezcan pantallasos blancos, principalmente en las
	 * animaciones de Rockford
	 */
	public void cachearImagenes() {
		for (TipoEntidad t: TipoEntidad.values()) {
			try {
				getGraphics().drawImage(AdminImagen.getInstance().getImage(t), 0, 0, 1, 1, null);
			} catch (ImageNotFoundException e) {
				getGraphics().drawImage(AdminImagen.getInstance().getErrorImage(), 0, 0, 1, 1, null);
			}
		}
		removeAll();
	}
}
