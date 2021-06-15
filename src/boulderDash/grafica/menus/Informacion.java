package boulderDash.grafica.menus;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import boulderDash.complementos.ImageNotFoundException;
import boulderDash.controlador.TipoEntidad;
import boulderDash.grafica.AdminFont;
import boulderDash.grafica.AdminImagen;
import boulderDash.grafica.Boton;
import boulderDash.grafica.Grafica;
import boulderDash.grafica.PanelPrincipal;

public class Informacion extends PanelPrincipal {
 static final long serialVersionUID = 1L;
	private static final String DESCRIPCION_BASICA = "INFORMACION BASICA:\n\n\nEL JUEGO CONSISTE EN QUE EL PERSONAJE PRINCIPAL, ROCKFORD, OBTENGA SUFICIENTES DIAMANTES PARA DESBLOQUEAR LA PUERTA DE SALIDA AL SIGUIENTE NIVEL.\n\n\nADEMAS, CADA DIAMANTE SUMA PUNTOS, JUNTO CON EL TIEMPO, PARA SER GUARDADO EN UNA TABLA DE MEJORES PUNTAJES\n\n\nSELECCIONE UNA DE LAS ENTIDADES QUE SE ENCUENTRA ARRIBA PARA MAS INFORMACION";
	private static final String DESCRIPCION_CONTROLES = "CONTROLES:\n\n\nESC: SALE DEL JUEGO AL MENU PRINCIPAL\n\n\nP: PAUSA EL JUEGO\n\n\nR: REINICIAR NIVEL\n\n\nFLECHAS: MOVIMIENTO DE ROCKFORD";
	private static final String DESCRIPCION_ROCKFORD = "ROCKFORD:\n\n\nES EL PERSONAJE QUE CONTROLA EL JUGADOR. DISPONE DE 4 VIDAS, Y SU OBJETIVO ES OBTENER SUFICIENTES DIAMANTES PARA QUE SE ABRA LA SALIDA\n\n\nPARA INFORMACION SOBRE LOS CONTROLES VEA CONTROLES";
	private static final String DESCRIPCION_ROCA = "ROCAS:\n\n\nES UN OBJETO INANIMADO QUE POSEE GRAVEDAD, PUEDE CAER SI NO HAY NADA DEBAJO O PATINAR HACIA LOS LADOS SI HAY UN DIAMANTE, MURO, U OTRA ROCA. SI VIENE CAYENDO Y ROCKFORD ESTA DEBAJO, LO MATA.";
	private static final String DESCRIPCION_DIAMANTE = "DIAMANTES:\n\n\nSON LOS OBJETOS QUE DEBE CONSEGUIR ROCKFORD, CADA UNO SUMA UN PUNTAJE. AL IGUAL QUE LA ROCA TIENE GRAVEDAD Y PUEDE MATAR A ROCKFORD.";
	private static final String DESCRIPCION_BASURA = "BASURA:\n\n\nES UN OBJETO INANIMADO, Y ROCKFORD PUEDE PASAR A TRAVES DE LA MISMA, DEJANDO UN VACIO A SU PASO";
	private static final String DESCRIPCION_SALIDA = "SALIDA:\n\n\nUNA VEZ QUE ROCKFORD OBTIENE LOS DIAMANTES NECESARIOS ESTA APARECE, Y DEBE LLEGAR ANTES DE QUE SE TERMINE EL TIEMPO";
	private static final String DESCRIPCION_MARIPOSA = "MARIPOSA:\n\n\nSE MUEVE EN SENTIDO ANTIHORARIO, Y SI ROCKFORD SE ENCUENTRA EN UN CASILLERO ADYACENTE, EXPLOTA MATANDO A ROCKFORD. TAMBIEN MUERE SI LE CAE UNA ROCA ENCIMA. AL MORIR DEJA DIAMANTES";
	private static final String DESCRIPCION_LUCIERNAGA = "LUCIERNAGA:\n\n\nSE MUEVE EN SENTIDO HORARIO, Y SI ROCKFORD SE ENCUENTRA EN UN CASILLERO ADYACENTE EXPLOTA, MATANDO A ROCKFORD. MUERE SI LE CAE UNA ROCA ENCIMA";
	
	
	private JLabel tutorial;
	
	private Boton indicadores;
	private Boton controles;
	private JLabel entidades;
	private Boton rockford;
	private Boton mariposa;
	private Boton luciernaga;
	private Boton roca;
	private Boton diamante;
	private Boton salida;
	private Boton basura;
	private Boton informacionbasica;
	
	private JTextArea wall;
	
	private Boton menuPrincipal;
	
	public Informacion(final MenuPrincipal menu) {
		
		tutorial = new JLabel(new ImageIcon(Grafica.class.getResource("img/tutorial.jpg")));
		tutorial.setHorizontalAlignment(SwingConstants.LEFT);
		tutorial.setVerticalAlignment(SwingConstants.TOP);
		tutorial.setBounds(48, 120, 864, 400);
		
		wall = new JTextArea(DESCRIPCION_BASICA);
		wall.setFont(AdminFont.getFont(20f));
		wall.setForeground(new Color(230, 230, 230));
		wall.setEditable(false);
		wall.setOpaque(false);
		wall.setWrapStyleWord(true);
		wall.setLineWrap(true);
		wall.setBounds(100, 150, 760, 350);
		add(wall);
		
		menuPrincipal = new Boton("MENU PRINCIPAL", 240, 520);
		menuPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarInicio();
			}
		});
		add(menuPrincipal);
		
		informacionbasica = new Boton("INFO BASICA", 100, 50, 286, 36);
		informacionbasica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_BASICA);
				repaint();
			}
		});
		add(informacionbasica);
		
		indicadores = new Boton("INDICADORES", 386, 50, 286, 36);
		indicadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(tutorial);
				repaint();
			}
		});
		add(indicadores);
		
		controles = new Boton("CONTROLES", 672, 50, 286, 36);
		controles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_CONTROLES);
				repaint();
			}
		});
		add(controles);
		
		entidades = new JLabel("ENTIDADES");
		entidades.setBounds(239, 86, 230, 36);
		entidades.setFont(AdminFont.getFont(20F));
		entidades.setHorizontalAlignment(SwingConstants.CENTER);
		entidades.setVerticalAlignment(SwingConstants.CENTER);
		entidades.setForeground(new Color(230, 230, 230));
		add(entidades);
		
		try {
			rockford = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.ROCKFORD_NORMAL), 479, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			rockford = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 479, 86, 36, 36);
		}
		rockford.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_ROCKFORD);
				repaint();
			}
		});
		add(rockford);
		
		try {
			roca = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.ROCA), 515, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			roca = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 515, 86, 36, 36);
		}
		roca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_ROCA);
				repaint();
			}
		});
		add(roca);
		
		try {
			diamante = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.DIAMANTE), 551, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			diamante = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 551, 86, 36, 36);
		}
		diamante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_DIAMANTE);
				repaint();
			}
		});
		add(diamante);
		
		try {
			salida = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.SALIDA), 587, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			salida = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 587, 86, 36, 36);
		}
		salida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_SALIDA);
				repaint();
			}
		});
		add(salida);
		
		try {
			mariposa = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.MARIPOSA), 623, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			mariposa = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 623, 86, 36, 36);
		}
		mariposa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_MARIPOSA);
				repaint();
			}
		});
		add(mariposa);
		
		try {
			luciernaga = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.LUCIERNAGA), 659, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			luciernaga = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 659, 86, 36, 36);
		}
		luciernaga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_LUCIERNAGA);
				repaint();
			}
		});
		add(luciernaga);
		
		try {
			basura = new Boton(AdminImagen.getInstance().getImageIcon(TipoEntidad.BASURA), 695, 86, 36, 36);
		} catch (ImageNotFoundException e) {
			basura = new Boton(AdminImagen.getInstance().getErrorImageIcon(), 695, 86, 36, 36);
		}
		basura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(wall);
				remove(tutorial);
				
				add(wall);
				wall.setText(DESCRIPCION_BASURA);
				repaint();
			}
		});
		add(basura);
	}
}
