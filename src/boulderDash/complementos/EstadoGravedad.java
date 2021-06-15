package boulderDash.complementos;

import boulderDash.entidades.EntidadConGravedad;

/**
 * 
 * Clase abstracta que se encarga del comportamiento de los objetos con gravedad
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EstadoGravedad {
	
	/**
	 * 
	 * Comportamiento "gravitatorio"
	 * 
	 * @param entidad que tiene que caer
	 * @return true si debe cambiar el estado entre cayendo y estatico
	 */
	public abstract boolean comportamiento(EntidadConGravedad entidad);
	
	public boolean isCayendo() {
		return false;
	}
	
	public boolean isEstatico() {
		return false;
	}
	
}
