package boulderDash.grafica.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import boulderDash.Juego;
import boulderDash.grafica.AdminFont;
import boulderDash.grafica.Boton;
import boulderDash.grafica.EntradaTabla;
import boulderDash.grafica.Grafica;
import boulderDash.grafica.PanelPrincipal;

public class IngresarPuntuacion extends PanelPrincipal{

	private static final long serialVersionUID = 1L;
	private static final String INGRESAR_NOMBRE = "FELICITACIONES! TU SCORE ES DIGNO DE SER GUARDADO PARA LA POSTERIDAD";
	private static final String NOMBRE_REPETIDO_CON_SCORE_MENOR = "EL NOMBRE INGRESADO YA EXISTE, CON UNA PUNTUACION MENOR. SI DESEA SOBREESCRIBIRLO SELECCIONE \"SOBREESCRIBIR\", O ELIJA OTRO NOMBRE";
	private static final String NOMBRE_REPETIDO_CON_SCORE_MAYOR = "EL NOMBRE INGRESADO YA EXISTE, CON UNA PUNTUACION MAYOR. SI DESEA SOBREESCRIBIRLO SELECCIONE \"SOBREESCRIBIR\", O ELIJA OTRO NOMBRE";
	private static final String NOMBRE_INVALIDO = "NOMBRE NO VALIDO, DEBE TENER AL MENOS 2 CARACTERES Y NO CONTENER ESPACIOS";
	
	private JTextArea titulo;
	private JTextField entrada;
	private JCheckBox sobreescribir;
	private Boton botonGuardar;
	private Boton botonMenu;
	
	public IngresarPuntuacion(final MenuPrincipal menu) {
		
		Grafica.getInstance().ordenarTabla();
		
		titulo = new JTextArea(INGRESAR_NOMBRE);
		titulo.setFont(AdminFont.getFont(20f));
		titulo.setForeground(Color.WHITE);
		titulo.setEditable(false);
		titulo.setOpaque(false);
		titulo.setWrapStyleWord(true);
		titulo.setLineWrap(true);
		titulo.setBounds(100, 150, 760, 150);
		add(titulo);
		
		entrada = new JTextField();
		entrada.setFont(AdminFont.getFont(20f));
		entrada.setBackground(Color.WHITE);
		entrada.setBounds(100, 300, 760, 45);
		entrada.setBorder(BorderFactory.createEmptyBorder());
		add(entrada);
		
		sobreescribir = new JCheckBox(" SOBREESCRIBIR ");
		sobreescribir.setFont(AdminFont.getFont(20f));
		sobreescribir.setForeground(Color.WHITE);
		sobreescribir.setBounds(100, 390, 480, 36);
		sobreescribir.setOpaque(false);
		sobreescribir.setHorizontalAlignment(SwingConstants.CENTER);
		add(sobreescribir);
		
		botonGuardar = new Boton("GUARDAR", 580, 390, 280, 36);
		botonGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String aux = entrada.getText();
				if (aux.length() >= 2 && !aux.contains(" ")) {
					if (aux.length() >= 20)
						aux = aux.substring(0, 20);
					
					EntradaTabla entradaConMismoNombre = null;
					
					for (EntradaTabla e: Grafica.getInstance().getEntradas())
						if (e.getNombre().equals(aux))
							entradaConMismoNombre = e;
					
					// Si el nombre no esta repetido, o se eligio sobreescribir, se empieza a guardar
					if (entradaConMismoNombre == null || sobreescribir.isSelected()) {
						EntradaTabla entradaGuardar = new EntradaTabla(aux,
							Juego.getInstance().getPuntuacionGeneral(),
							Juego.getInstance().getTiempoTotal());
					
						
						// Si se eligio sobreescribir el nombre, que borre la 
						// entrada con ese nombre
						if (entradaConMismoNombre != null && sobreescribir.isSelected())
							Grafica.getInstance().getEntradas().remove(entradaConMismoNombre);
						
						// Si hay mas de 20 o mas entradas, borra la de menor
						// score
						if (Grafica.getInstance().getEntradas().size() >= 20) {
							Collections.sort(Grafica.getInstance().getEntradas(), new Comparator<EntradaTabla>() {
								@Override
								public int compare(EntradaTabla o1, EntradaTabla o2) {
									return o1.getScore() - o2.getScore();
								}
							});
							
							Grafica.getInstance().getEntradas().remove(19);
						}
						
						// Guarda en la lista la entrada
						Grafica.getInstance().getEntradas().add(entradaGuardar);
						
						Grafica.getInstance().guardarTabla();
						
						menu.mostrarInicio();
						
					} else {
						// Si el nombre ya existe, se fija si el score es mayor
						// que el de la tabla y avisa
						if (Juego.getInstance().getPuntuacionGeneral() >= entradaConMismoNombre.getScore())
							titulo.setText(NOMBRE_REPETIDO_CON_SCORE_MENOR);
						else
							titulo.setText(NOMBRE_REPETIDO_CON_SCORE_MAYOR);
					}
					
				} else {
					titulo.setText(NOMBRE_INVALIDO);
				}
			}
		});
		add(botonGuardar);

		botonMenu = new Boton("MENU PRINCIPAL", 240, 450);
		botonMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarInicio();
			}
		});
		add(botonMenu);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		entrada.setText(entrada.getText().toUpperCase());
		
		repaint();
	}
}
