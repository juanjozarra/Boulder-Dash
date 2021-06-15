package boulderDash.entidades;

import boulderDash.Juego;
import boulderDash.complementos.OrdenMovimiento;
import boulderDash.complementos.Posicion;
import boulderDash.interfaces.Killable;

/**
 * 
 * Clase padre de todas las entidades que tienen un movimiento y explotan
 * cuando entran en contacto con Rockford
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class EntidadExplosiva extends EntidadHostil implements Killable {
	
	// Atributos ==============================================================
	
	protected OrdenMovimiento ordenMov;
	
	// Constructor ==============================
	
	public EntidadExplosiva(int x, int y) {
		super(x, y);
	}

	/**
	 * 
	 * Comportamiento de la EntidadExplosiva. Si Rockford se encuentra en un
	 * casillero adyacente, explota y lo mata. Si no se mueve dependiendo el
	 * sentido que le corresponde.
	 * 
	 */
	@Override
	public void comportamiento() {
		// Variable auxiliar
		int distancia = Integer.MAX_VALUE;
		
		// Juego auxiliar para manejarlo mas agil
		Juego juego = Juego.getInstance();
		
		// Mide la distancia NO diagonal hacia Rockford, si es que esta vivo
		if (Rockford.getInstance().estaVivo())
			distancia = Posicion.getDistancia(Rockford.getInstance().getPosicion(), this.getPosicion());
		
		if (distancia == 1)
			// Si Rockford esta al lado, explota
			this.kill();
		else {
			// Si Rockford no esta al lado, se mueve
			if (juego.esValida(Posicion.sumar(getPosicion(), ordenMov.getDireccionActual())) &&
					juego.getEntidad(Posicion.sumar(getPosicion(), ordenMov.getDireccionActual())).esVacio())
				// Si la posicion a la que se quiere mover esta vacia, se mueve
				this.mover(ordenMov.getDireccionActual());
			else
				// Si no esta vacio, cambia la direccion
				ordenMov.siguienteDireccion();
		}
	}

	/**
	 * 
	 * Explota, en un cuadrado de 3x3
	 * 
	 */
	@Override
	public void kill() {
		// Variables auxiliares
		int i, j;
		int centerAbsolutX = this.getPosicion().getX();
		int centerAbsolutY = this.getPosicion().getY();
		Juego juego = Juego.getInstance();
		
		// Recorre un cuadrado de 3x3, con centro en la posicion de la entidad
		// y radio de 1.
		for (i = -1; i <= 1; i++) {
			for (j = -1; j <= 1; j++) {
				// Si la posicion actual es explotable, se crea una explosion,
				// si en la ubicacion esta Rockford, lo mata
				if (juego.getEntidad(centerAbsolutX + i,
						centerAbsolutY + j).esExplotable()) {
					if (juego.getEntidad(centerAbsolutX + i, centerAbsolutY + j).esRockford())
						Rockford.getInstance().kill();
					else
						juego.agregarEntidad(new Explosion(centerAbsolutX + i, centerAbsolutY + j));
				}
			}
		}
	}
}
