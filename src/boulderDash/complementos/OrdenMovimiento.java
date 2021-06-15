package boulderDash.complementos;

/**
 * 
 * Clase utilizada por las entidades con movimiento automatico, para saber en
 * que direccion se tiene que mover
 * 
 * @author Paradiso - Zarragoicoeche
 *
 */

public abstract class OrdenMovimiento {

	// Atributos ==============================================================
	
	// Guarda la direccion actual, para saber cual sera la proxima
	protected int direccionActual;
	protected Direccion[] ordenDireccion;
	
	// Constructor
	public OrdenMovimiento() {
		ordenDireccion = new Direccion[4];
		direccionActual = 0;
	}
	
	// Metodos ================================================================
	
	/**
	 * Devuelve la direccion actual a la cual se esta moviendo la entidad
	 * 
	 * @return posicion relativa hacia donde se mueve
	 */
	public Direccion getDireccionActual() {
		// Transforma el codigo interno usado para mantener la posicion en una
		// posicion consiza
		return ordenDireccion[direccionActual];
	}
	
	/**
	 * 
	 * Devuelve la siguiente posicion que debe seguir la entidad. Horario o
	 * anti-horario dependiendo que clase se ha instanciado
	 * 
	 * @return posicion relativa hacia donde se debe mover
	 */
	public Direccion siguienteDireccion() {
		if (direccionActual == 3)
			// Si esta en el final del array, reinicia
			direccionActual = 0;
		else
			// Si esta en otra posicion, aumenta a la siguiente
			direccionActual++;
		
		return getDireccionActual();
	}
}
