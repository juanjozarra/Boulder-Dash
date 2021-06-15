package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;
import boulderDash.interfaces.Comportable;

/**
 * 
 * Clase de la entidad Salida
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Salida extends EntidadInmovil implements Comportable {

	// Constructor ==============================
	
	public Salida(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean esExplotable() {
		return false;
	}
	
	@Override
	public void comportamiento() {
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.SALIDA;
	}

}
