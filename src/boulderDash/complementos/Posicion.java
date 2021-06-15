package boulderDash.complementos;

/**
 * 
 * Clase que contiene coordenadas x e y
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Posicion {
	
	// Atributos ================================
	
	// Primitivos
	
	private int x;
	private int y;
	
	// Constructor ==============================
	
	public Posicion(Posicion p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public Posicion(int absoluteX, int absoluteY) {
		this.x = absoluteX;
		this.y = absoluteY;
	}

	// Metodos ==================================
	
	// Metodos basicos
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	// Metodos estaticos
	
	/**
	 * 
	 * Devuelve la distancia entre dos puntos, medida como la distancia en X
	 * mas la distancia en Y, no en diagonal.
	 * 
	 * @param a primera posicion
	 * @param b segunda posicion
	 * @return distancia
	 */
	public static int getDistancia(Posicion a, Posicion b) {
		int distX = Math.abs(a.getX() - b.getX());
		int distY = Math.abs(a.getY() - b.getY());
		
		return distX + distY;
	}

	// Metodos complejos
	
	/**
	 * 
	 * Cambia los valores de las coordenadas X e Y, reemplazandolas por las
	 * recibidas por parametro
	 * 
	 * @param x coordenada en X
	 * @param y coordenada en Y
	 */
	public void moverAbsoluto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * Mueve la entidad en la direccion pasada por parametro
	 * 
	 * @param dir Direccion en la que se quiere mover
	 */
	public void mover(Direccion dir) {
		this.x += dir.getX();
		this.y += dir.getY();
	}
	
	/**
	 * 
	 * Suma a una posicion una direccion, para saber cual es la posicion final
	 * si la entidad se mueve en esa direccion
	 * 
	 * @param pos Posicion base
	 * @param dir Direccion donde se quiere mover
	 * @return Posicion donde quedaria
	 */
	public static Posicion sumar(Posicion pos, Direccion dir) {
		return new Posicion(pos.getX() + dir.getX(), pos.getY() + dir.getY());
	}
	
	/**
	 * 
	 * Suma a una posicion dos direcciones, por ejemplo para movrse en
	 * diagonal
	 * 
	 * @param pos Posicion base
	 * @param dir1 Direccion donde se quiere mover
	 * @param dir2 Direccion donde se quiere mover
	 * @return Posicion donde quedaria
	 */
	public static Posicion sumar(Posicion pos, Direccion dir1, Direccion dir2) {
		return new Posicion(pos.getX() + dir1.getX() + dir2.getX(), pos.getY() + dir1.getY() + dir2.getY());
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y +")";
	}
}
