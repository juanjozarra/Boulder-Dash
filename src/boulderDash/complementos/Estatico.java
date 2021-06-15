package boulderDash.complementos;

import boulderDash.Juego;
import boulderDash.entidades.EntidadConGravedad;
import boulderDash.entidades.Muro;

/**
 * 
 * Clase concreta de EstadoGravedad, utilizada cuando el objeto esta quieto
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Estatico extends EstadoGravedad {

	/*
	 * (non-Javadoc)
	 * @see boulderDash.complementos.EstadoGravedad#comportamiento(boulderDash.entidades.EntidadConGravedad)
	 */
	@Override
	public boolean comportamiento(EntidadConGravedad e) {
		// Referencia a mapa para mayor practicidad
		Juego juego = Juego.getInstance();
		
		Posicion pos = e.getPosicion();

		// Si el espacio debajo esta vacio, devuelve true para cambiar a cayendo
		if ( juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)).esVacio() ) {
			return true;
		}
		
		// Si el espacio debajo es una Entidad con gravedad (Roca o diamante)
		// o un Muro trata de patinar. Si puede patinar devuelve true para 
		// cambiar el estado
		else if ( juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)) instanceof EntidadConGravedad ||
				  juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)) instanceof Muro) {
			
			// Se fija si puede patinar hacia la derecha
			if ( juego.getEntidad(Posicion.sumar(pos, Direccion.DERECHA)).esVacio() &&
				 juego.getEntidad(Posicion.sumar(pos, Direccion.DERECHA, Direccion.ABAJO)).esVacio() )
				return true;

			// Si no puede patinar a la derecha, trata a la izquierda
			else if ( juego.getEntidad(Posicion.sumar(pos, Direccion.IZQUIERDA)).esVacio() &&
				      juego.getEntidad(Posicion.sumar(pos, Direccion.IZQUIERDA, Direccion.ABAJO)).esVacio() )
				return true;
			
			// Si no puede patinar a ningun lado, retorna false para seguir estatico
			else
				return false;
		} 
		
		// Si no puede patinar ni caer, sigue estatico
		return false;
	}

	@Override
	public boolean isEstatico() {
		return true;
	}
}
