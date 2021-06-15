package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad Vacio
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Vacio extends EntidadInmovil {
	
	// Constructor ==============================
	
	public Vacio(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean esVacio() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Vacio [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.VACIO;
	}
}
