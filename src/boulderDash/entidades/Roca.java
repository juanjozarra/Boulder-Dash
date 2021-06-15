package boulderDash.entidades;

import boulderDash.complementos.EstadoGravedad;
import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase de la entidad Roca
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Roca extends EntidadConGravedad {
	
	// Constructor ==============================
	
	public Roca(int x, int y, EstadoGravedad estado) {
		super(x, y, estado);
	}
	
	// Metodos ================================================================
	
	@Override
	public boolean esRoca() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Roca [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.ROCA;
	}

}
