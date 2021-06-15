package boulderDash.entidades;

import boulderDash.complementos.Posicion;
import boulderDash.controlador.TipoEntidad;

/**
 * 
 * Clase padre de todas las entidades y personajes. Posee una ID unica, y
 * posicion.
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public abstract class Entidad {
	
	// Atributos ================================
	
	private static int lastID = 0;
	
	protected Posicion posicion;
	
	private final int id;
	
	// Constructor ==============================
	
	public Entidad() {
		id = Entidad.nextID();
	}
	
	/**
	 * 
	 * Crea una nueva entidad en la posicion X Y
	 * 
	 * @param x coordenada en X
	 * @param y coordenada en Y
	 * 
	 */
	public Entidad(int x, int y) {
		posicion = new Posicion(x, y);
		id = Entidad.nextID();
	}
	
	
	// Metodos ==================================
	
	// Metodo para asignarle una ID unica a cada entidad
	private static int nextID() {
		return lastID++;
	}
	
	/**
	 * Devuelve la ID de la entidad
	 * 
	 * @return ID de la entidad
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Devuelve la posicion de la entidad
	 * 
	 * @return
	 */
	public Posicion getPosicion() {
		return posicion;
	}
	
	/**
	 * Elimina la posicion de la Entidad
	 */
	public void borrarPosicion() {
		posicion = null;
	}
	
	/**
	 * Pregunta si la entidad es un Vacio
	 * 
	 * @return True si es vacio, False si no
	 */
	public boolean esVacio() {
		return false;
	}
	
	/**
	 * Pregunta si la entidad es Basura
	 * 
	 * @return True si es basure, False si no
	 */
	public boolean esBasura() {
		return false;
	}
	
	/**
	 * Pregunta si la entidad es un Diamante
	 * 
	 * @return True si es diamante, False si no
	 */
	public boolean esDiamante() {
		return false;
	}
	
	/**
	 * Pregunta si la entidad es una Roca
	 * 
	 * @return True si es una Roca, False si no
	 */
	public boolean esRoca() {
		return false;
	}
	
	/**
	 * Pregunta si la entidad es explotable, es decir, que puede ser reemplazado
	 * por una explosion
	 * 
	 * @return True si es explotable, false caso contrario
	 */
	public boolean esExplotable() {
		return true;
	}
	
	/**
	 * Pregunta si la entidad es Rockford
	 * 
	 * @return True si es Rockford, False caso contrario
	 */
	public boolean esRockford() {
		return false;
	}
	
	public abstract TipoEntidad getTipo();
}
