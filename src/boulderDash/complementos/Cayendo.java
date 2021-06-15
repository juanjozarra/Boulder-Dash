package boulderDash.complementos;

import boulderDash.Juego;
import boulderDash.entidades.EntidadConGravedad;
import boulderDash.entidades.Muro;
import boulderDash.interfaces.Killable;


/**
 * 
 * Clase concreta de EstadoGravedad, utilizada cuando el objeto esta cayendo
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */


public class Cayendo extends EstadoGravedad {


	/*
	 * (non-Javadoc)
	 * @see boulderDash.complementos.EstadoGravedad#comportamiento(boulderDash.entidades.EntidadConGravedad)
	 */
	@Override
	public boolean comportamiento(EntidadConGravedad e) {
		// Variable auxiliar para manejar las referencias mas facil
		Juego juego = Juego.getInstance();

		Posicion pos = e.getPosicion();
		// Verifica que el espacio debajo sea valido
		
		if (juego.esValida(Posicion.sumar(pos, Direccion.ABAJO))) {
			// Si el espacio debajo esta vacio, se mueve
			if (juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)).esVacio())
				e.mover(Direccion.ABAJO);
			
			
			// Si el espacio debajo no esta vacio, se fija si lo puede matar
			else if ( juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)) instanceof Killable ) {
				Killable entidadDebajo = ( Killable ) juego.getEntidad(Posicion.sumar(pos,  Direccion.ABAJO));
				
				// Si lo puede matar (instancia de Killable) lo mata
				entidadDebajo.kill();
			} 
			
			// Si el espacio debajo es una Entidad con gravedad (Roca o diamante)
			// trata de patinar. Tambien patina si es un muro
			else if ( juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)) instanceof EntidadConGravedad
					  || juego.getEntidad(Posicion.sumar(pos, Direccion.ABAJO)) instanceof Muro ) {
				
				// Se fija si puede patinar hacia la derecha
				if ( juego.getEntidad(Posicion.sumar(pos, Direccion.DERECHA)).esVacio() &&
					 juego.getEntidad(Posicion.sumar(pos, Direccion.DERECHA, Direccion.ABAJO)).esVacio() )
					e.mover(Direccion.DERECHA);

				// Si no puede patinar a la derecha, trata a la izquierda
				else if ( juego.getEntidad(Posicion.sumar(pos, Direccion.IZQUIERDA)).esVacio() &&
					      juego.getEntidad(Posicion.sumar(pos, Direccion.IZQUIERDA, Direccion.ABAJO)).esVacio() )
					e.mover(Direccion.IZQUIERDA);
				
				// Si no puede patinar a ningun lado, retorna true para cambiar
				// el estado a "Estatico"
				else
					return true;
			} 
			
			// Si no puede ni moverse, ni patinar, ni matar lo que hay debajo
			// devuelve true para cambiar el estado a "Cayendo"
			else {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isCayendo() {
		return true;
	}

}
