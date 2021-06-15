package boulderDash.grafica;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import boulderDash.Juego;
import boulderDash.grafica.menus.MenuPrincipal;


public class Grafica extends JFrame {

	private static final long serialVersionUID = 1L;

	public enum Modo {JUEGO, MENU}
	
	private static Grafica grafica = null;
	private int minScore;
	private List<EntradaTabla> entradas;
	
	JPanel vista;
	private Modo modo;
	
	private Grafica(){
	}
	
	public static Grafica getInstance() {
		if (grafica == null)
			grafica = new Grafica();
		return grafica;
	}
	
	/**
	 * Pasa el juego a modo Menu, por default va al menu principal
	 * 
	 */
	public void modoMenu() {
		modo = Modo.MENU;
		vista = new MenuPrincipal();
		this.setContentPane(vista);
		this.pack();
	}
	
	/**
	 * Pasa la grafica a modo Juego
	 *
	 * @param nivel
	 */
	public void modoJuego() {
		
		modo = Modo.JUEGO;
		
		vista = new PanelJuego();
		this.setContentPane(vista);
		PanelJuego aux = (PanelJuego) vista;
		aux.cachearImagenes();
		this.pack();
	}	
	
	/**
	 * Actualiza la visual del juego, la informacion se la pide al modelo
	 * @throws IOException Si no encuentra los archivos de imagen, lanza una excepcion
	 * 
	 */
	public void actualizar() throws IOException {
		if (modo == Modo.JUEGO) {
			vista.repaint();
			pack();
		}
		
	}
	
	/**
	 * Inicia la grafica, crea la ventana principal y lo deja en el menu
	 * 
	 */
	public void iniciar() {
		modoMenu();		
		iniciarTabla();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Boulder Dash");
		try {
			setIconImage(new ImageIcon(getClass().getResource("img/icon.png")) .getImage());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
	
	public void iniciarTabla() {
		
		ObjectInputStream input;
		entradas = new LinkedList<EntradaTabla>();
		boolean EOF = false;
		try {
			input = new ObjectInputStream(new FileInputStream("score.dat"));
			
			while(!EOF) {
				entradas.add((EntradaTabla) input.readObject());
			}
			
		} catch (EOFException e ) {
			EOF = true;

			// Las ordena correctamente, de manera que el 0 sea el puntaje mas
			// alto
			ordenarTabla();
			
			// Si hay 20 entradas, setea como score minimo para entrar la pun
			// tuacion mas baja
			if (entradas.size() == 20)
				minScore = entradas.get(entradas.size() - 1).getScore();
			else
				// Si no, 0, al no haber 20 entradas cualquier puntaje es alto
				minScore = 0;
		} catch (FileNotFoundException e) {
			File aux = new File("score.dat");
			try {
				aux.createNewFile();
				minScore = 0;
			} catch (IOException e1) {
				e1.printStackTrace();
				minScore = Integer.MAX_VALUE;
			}
		} catch (IOException e) {
			e.printStackTrace();
			minScore = Integer.MAX_VALUE;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			minScore = Integer.MAX_VALUE;
		}
	}
	
	public void vaciarTabla() {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream("score.dat"));
			out.write("");
			entradas.removeAll(entradas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void guardarTabla() {
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("score.dat"));
			
			for (EntradaTabla e : Grafica.getInstance().getEntradas())
				out.writeObject(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	
	public void ordenarTabla() {
		// Las ordena por mayor puntaje
		Collections.sort(entradas, new Comparator<EntradaTabla>() {
			@Override
			public int compare(EntradaTabla o1, EntradaTabla o2) {
				if (o1.getScore() == o2.getScore())
					return o1.getTime() -  o2.getTime();
				else
					return o2.getScore() - o1.getScore();
			}
		});
	}
	
	/**
	 * Metodo invocado cuando se pierde, asi la grafica muestra el puntaje o lo
	 * que quiera hacer.
	 * 
	 */
	public void gameOver() {
		modoMenu();
		
		MenuPrincipal aux = (MenuPrincipal) vista;
		
		if (Juego.getInstance().getPuntuacionGeneral() > minScore) {
			aux.ingresarPuntuacion();
		}
	}
	
	public int getNumeroEntradas() {
		return entradas.size();
	}

	/**
	 * Devuelve la lista con el top de entradas
	 * 
	 * @return
	 */
	public List<EntradaTabla> getEntradas() {
		ordenarTabla();
		return entradas;
	}

}
