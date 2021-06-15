package boulderDash.grafica;

import java.io.Serializable;

public class EntradaTabla implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private int score;
	private int time;
	
	public EntradaTabla(String nombre, int score, int time) {
		this.nombre = nombre;
		this.score = score;
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public int getScore() {
		return score;
	}

	public int getTime() {
		return time;
	}
	
	
}
