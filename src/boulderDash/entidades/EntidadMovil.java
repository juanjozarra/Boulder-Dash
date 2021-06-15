package boulderDash.entidades;

import boulderDash.Juego;
import boulderDash.complementos.Direccion;
import boulderDash.complementos.Posicion;

/**
 * 
 * Clase padre de todas las entidades moviles. Rockford, las entidades hostiles
 * y las que poseen gravedad
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EntidadMovil extends Entidad {

	// Constructor ============================================================
	
	
	public EntidadMovil() {
		super();
	}
	
	public EntidadMovil(int absoluteX, int absoluteY) {
		super(absoluteX, absoluteY);
	}
	
	// Metodos ================================================================
	
	
	/**
	 * 
	 * Mueve relativamente a la entidad en la direccion especificada por
	 * parametro.
	 * 
	 * @param dir Direccion hacia donde se quiere mover
	 */
	public void mover(Direccion dir) {
		Juego juego = Juego.getInstance();
		Posicion nueva = new Posicion(this.getPosicion().getX() + dir.getX(), this.getPosicion().getY() + dir.getY());
		
		if (juego.esValida(nueva)) {
			
			// Mueve la entidad en el mapa
			juego.mover(this, nueva);
			
			// Cambia la posicion interna
			this.posicion.mover(dir);
			
		}
	}
}
