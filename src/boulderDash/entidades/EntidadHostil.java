package boulderDash.entidades;

import boulderDash.interfaces.Comportable;

/**
 * 
 * Clase padre de todas las entidades hostiles, la mariposa, luciernaga y ameba
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EntidadHostil extends EntidadMovil implements Comportable {
	
	// Constructor ==============================
	
	public EntidadHostil(int x, int y) {
		super(x, y);
	}

}
