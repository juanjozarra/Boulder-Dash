package boulderDash.entidades;

/**
 * 
 * Clase padre de todas las entidades no-moviles. Muros, vacio, basura, salida.
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EntidadInmovil extends Entidad {
	
	// Constructor ==============================
	
	public EntidadInmovil(int x, int y) {
		super(x, y);
	}
}
