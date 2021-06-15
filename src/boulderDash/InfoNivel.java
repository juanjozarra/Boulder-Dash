package boulderDash;

import java.util.Scanner;

import boulderDash.complementos.Posicion;
import boulderDash.controlador.Controlador;

/**
 * 
 * Contiene la informacion propia de cada nivel. El numero, la cantidad de
 * diamantes, la entrada y la salida
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class InfoNivel {
	
	// Primitivo
	
	private int nivel, diamanteMin, valorDiamante, valorDiamanteBonus, tiempo;
	
	private Posicion entrada;
	private Posicion salida;
	
	
	
	// Constructor privado usado como auxiliar
	private InfoNivel(int nivel,
			int diamanteMin,
			int valorDiamante,
			int valorDiamanteBonus,
			int tiempo,
			Posicion entrada,
			Posicion salida) {
		this.nivel = nivel;
		this.diamanteMin = diamanteMin;
		this.valorDiamante = valorDiamante;
		this.valorDiamanteBonus = valorDiamanteBonus;
		this.tiempo = tiempo;
		this.entrada = entrada;
		this.salida = salida;
	}
	
	public InfoNivel() {
		
	}
	
	public void setEntrada(Posicion entrada) {
		this.entrada = entrada;
	}

	public void setSalida(Posicion salida) {
		this.salida = salida;
	}
	
	// Metodos ==================================
	public int getNivel() {
		return nivel;
	}
	public int getDiamanteMin() {
		return diamanteMin;
	}
	public Posicion getEntrada() {
		return entrada;
	}
	public Posicion getSalida() {
		return salida;
	}

	public int getValorDiamante() {
		return valorDiamante;
	}

	public int getValorDiamanteBonus() {
		return valorDiamanteBonus;
	}

	public int getTiempo() {
		return tiempo;
	}

	/**
	 * Setea un nivel, y carga toda la informacion correspondiente del mismo
	 * desde un archivo
	 * 
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
		cargarInfo();
	}
	
	/**
	 * Devuelve una copia de el objeto InfoNivel al que es llamado. Usado para
	 * evitar que se modifique la informacion durante el juego.
	 * 
	 * @return Copia del objeto sobre el que es llamado
	 */
	public InfoNivel copy() {
		return new InfoNivel(
				nivel,
				diamanteMin,
				valorDiamante,
				valorDiamanteBonus,
				tiempo,
				null,
				null);
	}
	
	/**
	 * Carga la info del nivel actual desde un archivo pasado por parametro
	 * 
	 * @param path Direccion del archivo
	 */
	public void cargarInfo() {
		Scanner scanner = null;
		boolean encontro = false;
		String linea;
		String[] info;
		
		try {
			// Abre el archivo con un scanner
			scanner = new Scanner(getClass().getResourceAsStream("level-info.config"));
			
			// Mientras no encontro el nivel actual y quedan lineas en el arc
			while (!encontro && scanner.hasNextLine()) {
				linea = scanner.nextLine();
				// Si la linea que se levanto corresponde al nivel actual
				if ((linea.charAt(0) != '#') &&
				    (Integer.parseInt(linea.split(":")[0]) == nivel)) {
					encontro = true;
					
					// Separa la linea por ":" y carga la informacion en las
					// variables correspondientes
					info = linea.split(":");
					
					diamanteMin = Integer.parseInt(info[1]);
					valorDiamante = Integer.parseInt(info[2]);
					valorDiamanteBonus = Integer.parseInt(info[3]);
					tiempo = Integer.parseInt(info[4]);
				}
			}
			
			if (!scanner.hasNextLine() && !encontro) {
				System.err.println("No se encontro el nivel " + nivel);
				Controlador.kill();
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}
	
}
