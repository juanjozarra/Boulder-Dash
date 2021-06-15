package boulderDash.grafica.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import boulderDash.controlador.Controlador;
import boulderDash.grafica.Boton;
import boulderDash.grafica.Grafica;
import boulderDash.grafica.PanelPrincipal;

public class Inicio extends PanelPrincipal {
	
	private static final long serialVersionUID = 1L;

	class EventoBotonNivel extends MouseAdapter {
		
		int nivel;
		
		public EventoBotonNivel(int nivel) {
			this.nivel = nivel;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Controlador.iniciaJuego(nivel);
		}
	}
	
	private Boton jugar;
	private Boton puntuacion;
	private Boton informacion;
	private Boton configuracion;
	private JLabel logo;
	
	public Inicio(final MenuPrincipal menu) {
		inicio(menu);
	}
	
	/**
	 * Grafica el inicio comun
	 * 
	 */
	public void inicio(final MenuPrincipal menu) {
		removeAll();
		
		logo = new JLabel(new ImageIcon(Grafica.class.getResource("img/bg.jpg")));
		logo.setBounds(289, 50, 382, 230);
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logo.setVerticalAlignment(SwingConstants.TOP);
		add(logo);
		
		jugar = new Boton("JUGAR", 240, 370);
		
		jugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jugar(menu);
			}
		});
		this.add(jugar);
		
		puntuacion = new Boton("PUNTUACION", 240, 430);
		puntuacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarPuntuacion();
			}
		});
		this.add(puntuacion);
		
		informacion = new Boton("INFORMACION", 240, 490);
		informacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarInformacion();
			}
		});
		this.add(informacion);
		
		configuracion = new Boton(new ImageIcon(Grafica.class.getResource("img/config.png")), 905, 20, 35, 35);
		configuracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarConfig();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				configuracion.setIcon(new ImageIcon(Grafica.class.getResource("img/config_yellow.png")));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				configuracion.setIcon(new ImageIcon(Grafica.class.getResource("img/config.png")));
			}
		});
		this.add(configuracion);
		
		repaint();
	}
	
	/**
	 * Grafica la eleccion de niveles
	 * 
	 */
	public void jugar(final MenuPrincipal menu) {
		this.removeAll();
		
		Boton aux;
		int i;
		
		logo = new JLabel(new ImageIcon(Grafica.class.getResource("img/bg.jpg")));
		logo.setBounds(289, 50, 382, 230);
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logo.setVerticalAlignment(SwingConstants.TOP);
		add(logo);
		
		// La posicion es (480 + ((i - 1) * ancho_boton) + (i - 1) * gap
		// pero simplificada
		
		// Primera fila de botones
		for (i = 1; i <= 5; i++) {
			aux = new Boton(String.format("%d", i), 180 + 90*i, 300, 60, 60);
			aux.addMouseListener(new EventoBotonNivel(i));
			add(aux);
		}
		
		// La segunda fila tiene 30 de gap entre la fila de arriba, sumado a
		// los 60 de los botones da 390 en el eje y
		for (i = 1; i <= 5; i++) {
			aux = new Boton(String.format("%d", i + 5), 180 + 90*i, 390, 60, 60);
			aux.addMouseListener(new EventoBotonNivel(i + 5));
			add(aux);
		}
		
		aux = new Boton("< VOLVER", 240, 480);
		aux.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				inicio(menu);
			}
		});
		add(aux);
		
		repaint();
	}

}
