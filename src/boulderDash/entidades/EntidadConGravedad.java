package boulderDash.entidades;

import boulderDash.complementos.Cayendo;
import boulderDash.complementos.EstadoGravedad;
import boulderDash.complementos.Estatico;
import boulderDash.interfaces.Comportable;

/**
 * 
 * Clase que padre de todas las entidades que tienen comportamiento
 * gravitatiorio
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EntidadConGravedad extends EntidadMovil implements Comportable {
	
	private EstadoGravedad estado;
	
	// Constructor ==============================
	
	/**
	 * 
	 * Constructor de EntidadConGravedad, ademas de las coordenadas X e Y
	 * recibe el estado en el cual se encuentra la entidad.
	 * 
	 * @param x coordenada en X
	 * @param y coordenada en Y
	 * @param estado gravitatorio (Cayendo o estatico)
	 */
	public EntidadConGravedad(int x, int y, EstadoGravedad estado) {
		super(x, y);
		this.estado = estado;
	}

	/**
	 * 
	 * Comportamiento de las clases con gravedad. Puede cambiar el estado
	 * dependiendo lo devuelto por los comportamientos especificos del estado
	 * actual
	 * 
	 */
	@Override
	public void comportamiento() {
		
		// Ejecuta el comportamiento correspondiente del estado
		boolean cambiar = estado.comportamiento(this);
		
		// Si el comportamiento retorna true, significa que debe cambiar de
		// cayendo a estatico o viceversa
		if (cambiar) {
			if (estado.isCayendo())
				estado = new Estatico();
			else
				estado = new Cayendo();
				
		}
	}
}
