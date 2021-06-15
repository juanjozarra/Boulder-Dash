package boulderDash.complementos;

/**
 * 
 * Objeto utilizado para indicar una direccion, ya sea de movimiento o relativa.
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public enum Direccion {
	ARRIBA(0, -1),
	ABAJO(0, 1),
	IZQUIERDA(-1, 0),
	DERECHA(1, 0),
	NULA(0, 0);
	
	// Atributos ==============================================================
	
	private final int x;
	private final int y;
	
	// Constructor ============================================================
	
	private Direccion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Metodos ================================================================
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
