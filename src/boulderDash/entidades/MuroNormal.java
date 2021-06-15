package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad MuroNormal
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class MuroNormal extends Muro {
	
	// Constructor ==============================
	
	public MuroNormal(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "MuroNormal [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.MURO_NORMAL;
	}

}
