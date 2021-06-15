package boulderDash.entidades;

import java.util.Random;

import boulderDash.Juego;
import boulderDash.controlador.TipoEntidad;

import boulderDash.interfaces.Comportable;

/**
 * 
 * Clase de la entidad Ameba
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Ameba extends EntidadHostil implements Comportable {

	// Constructor ==============================
	
	public Ameba(int x, int y) {
		super(x, y);
	}
	
	// Metodos ================================================================

	/**
	 * 
	 * Comportamientod de la Ameba, no recibe parametros ni devuelve nada
	 * Puede clonarse, para esto se comunica directamente con la clase Juego
	 * 
	 */
	@Override
	public void comportamiento() {
		// Random para elegir si crear el mapa
		Random rnd = new Random();
		
		// Variable auxiliar para agilizar  los metdos
		Juego juego = Juego.getInstance();
		
		// Variables para indicar el lugar
		int absolutX = this.getPosicion().getX();
		int absolutY = this.getPosicion().getY();
		
		// Obtiene un numero entre 0 y 3 para elegir donde tratar de duplicarse
		int direccion = rnd.nextInt(4);
		
		// Obtiene un random entre 0 y 9, si es 0  1, crea el objeto
		boolean crear = rnd.nextInt(10)  <= 1 ? true : false;
		
		// 0 -> arriba
		// 1 -> derecha
		// 2 -> abajo
		// 3 -> izquierda
		switch (direccion) {
		case 0:
			absolutX += 0;
			absolutY += -1;
			break;
		case 1:
			absolutX += 1;
			absolutY += 0;
			break;
		case 2:
			absolutX += 0;
			absolutY += 1;
			break;
		case 3:
			absolutX += -1;
			absolutY += 0;
			break;
		}
		
		if (crear && 
			(juego.getEntidad(absolutX, absolutY).esVacio() ||
			 juego.getEntidad(absolutX, absolutY).esBasura())) {
			juego.agregarEntidad(new Ameba(absolutX, absolutY));
		}
	}
	
	@Override
	public String toString() {
		return "Ameba [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.AMEBA;
	}

}
