package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad Basura
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Basura extends EntidadInmovil {

	// Constructor ==============================
	public Basura(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean esBasura() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Basura [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.BASURA;
	}

}
