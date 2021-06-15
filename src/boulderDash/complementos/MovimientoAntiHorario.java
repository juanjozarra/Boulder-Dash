package boulderDash.complementos;

/**
 * 
 * Subclase de OrdenMovimiento, utilizada para entidades que tienen un
 * movimiento en sentido anti horario
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class MovimientoAntiHorario extends OrdenMovimiento {

	/**
	 * Crea un array, conteniendo el movimiento en orden anti horario
	 * 
	 */
	public MovimientoAntiHorario() {
		super();
		
		ordenDireccion[0] = Direccion.ARRIBA;
		ordenDireccion[1] = Direccion.IZQUIERDA;
		ordenDireccion[2] = Direccion.ABAJO;
		ordenDireccion[3] = Direccion.DERECHA;
		
	}
}
