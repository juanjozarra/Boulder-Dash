package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad MuroIrrompible
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class MuroIrrompible extends Muro{

	// Constructor ==============================
	
	public MuroIrrompible(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "MuroIrrompible [" + this.getID() + "]";
	}
	
	@Override
	public boolean esExplotable() {
		return false;
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.MURO_IRROMPIBLE;
	}

}
