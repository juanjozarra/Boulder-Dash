package boulderDash.entidades;

import boulderDash.complementos.EstadoGravedad;
import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad Diamante
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Diamante extends EntidadConGravedad {
	
	// Constructor ==============================
	
	public Diamante(int x, int y, EstadoGravedad estado) {
		super(x, y, estado);
	}
	
	// Metodos ================================================================
	
	@Override
	public boolean esDiamante() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Diamante [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.DIAMANTE;
	}

}
