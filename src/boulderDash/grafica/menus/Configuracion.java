package boulderDash.grafica.menus;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import boulderDash.grafica.AdminFont;
import boulderDash.grafica.Boton;
import boulderDash.grafica.Grafica;
import boulderDash.grafica.PanelPrincipal;

public class Configuracion extends PanelPrincipal {
	
	private static final long serialVersionUID = 1L;
	private JLabel dropdownText;
	private JComboBox<Integer> dropdown;
	
	private JLabel borrarText;
	private Boton borrarPuntuacion;
	
	private JLabel logo;
	private Boton botonMenu;
	
	
	public Configuracion(final MenuPrincipal menu) {
		
		logo = new JLabel(new ImageIcon(Grafica.class.getResource("img/bg.jpg")));
		logo.setBounds(289, 50, 382, 230);
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logo.setVerticalAlignment(SwingConstants.TOP);
		add(logo);
		
		dropdownText = new JLabel("PUNTUACIONES A MOSTRAR:");
		dropdownText.setFont(AdminFont.getFont(20f));
		dropdownText.setForeground(Color.WHITE);
		dropdownText.setBounds(100, 320, 500, 36);
		add(dropdownText);
		
		Integer[] opciones = { 5, 10, 15, 20 };
		dropdown = new JComboBox<Integer>(opciones);
		dropdown.setFont(AdminFont.getFont(15f));
		dropdown.setBackground(new Color(65, 65, 254));
		dropdown.setForeground(Color.WHITE);
		dropdown.setBounds(630, 320, 230, 36);
		add(dropdown);

		borrarText = new JLabel("BORRAR PUNTUACIONES ["+Grafica.getInstance().getEntradas().size()+"]");
		borrarText.setFont(AdminFont.getFont(20f));
		borrarText.setForeground(Color.WHITE);
		borrarText.setBounds(100, 390, 500, 36);
		add(borrarText);
		
		borrarPuntuacion = new Boton("BORRAR", 630, 390, 230, 36);
		borrarPuntuacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Grafica.getInstance().vaciarTabla();
				borrarText.setText("BORRAR PUNTUACIONES ["+Grafica.getInstance().getEntradas().size()+"]");
			}
		});
		add(borrarPuntuacion);

		botonMenu = new Boton("MENU PRINCIPAL", 240, 460);
		botonMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.setNumTabla((Integer) dropdown.getSelectedItem());
				menu.mostrarInicio();
			}
		});
		add(botonMenu);
	}

}
