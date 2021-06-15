
package boulderDash.entidades;

import boulderDash.Juego;
import boulderDash.complementos.Direccion;
import boulderDash.complementos.Posicion;
import boulderDash.controlador.TipoEntidad;
import boulderDash.interfaces.Killable;

/**
 * 
 * Clase de la entidad Rockford, singleton
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Rockford extends EntidadMovil implements Killable {
	
	// Atributos ==============================================================
	
	// Variable auxiliar para testeo, si esta en false, rockford es inmortal
	// Puede (y va a) causar inestabilidad
	private static final boolean MORTAL = true;
	
	private static Rockford instancia = null;
	
	private int diamantes;
	private int vidas;
	private boolean vivo;
	
	// Esto es parte de la grafica, pero el codigo esta dentro de Rockford
	private int turnosInactivo;
	private Direccion ultimoMovimiento;
	private static final int TURNOS_INACTIVO = 20;
	
	// Constructor ==============================

	private Rockford() {
		vidas = 4;
	}
	
	public static Rockford getInstance() {
		if (instancia == null)
			instancia = new Rockford();
		return instancia;
	}
	// Metodos ================================================================
	
	// Getters y setters
	
	public int getVidas() {
		return vidas;
	}
	
	public int getDiamantes() {
		return diamantes;
	}
	
	public boolean estaVivo() {
		return vivo;
	}
	
	// Comportamiento
	
	/**
	 * 
	 * Comportamiento normal de Rockford, se trata de mover en la direccion
	 * provista por los parametros. Se mueve efectivamente si esta vacio,
	 * basura, diamante o si hay una Roca que puede desplazarse
	 * 
	 * @param relativeX direccion en X a moverse
	 * @param relativeY direccion en Y a moverse
	 */
	public void comportamiento(Direccion dir) {
		
		Juego juego = Juego.getInstance();
		
		if (juego.esValida(Posicion.sumar(getPosicion(), dir))) {
			
			// Se obtiene la entidad que hay en la posicion target para mayor agilidad
			Entidad entTarget = juego.getEntidad(Posicion.sumar(this.getPosicion(), dir));
			
			
			// Comienza a analisar que hay en el target
			if ( juego.getEntidad(Posicion.sumar(this.getPosicion(), dir)).esBasura()
					|| juego.getEntidad(Posicion.sumar(this.getPosicion(), dir)).esVacio()) {
				// Si el target es basura o vacio simplemente se mueve
				juego.eliminarEntidad(entTarget);
				mover(dir);
				
			} else if (entTarget.esDiamante()) {
				// Si es un diamante, lo elimina, aumenta la cantidad de diamantes
				// de Rockford y se mueve
				juego.eliminarEntidad(entTarget);
				diamantes++;
				
				juego.obtuvoDiamantes();
				
				mover(dir);
				
			} else if (entTarget.esRoca()) {
				// Si es una Roca la empuja, pero tiene que haber lugar y ser lateral
				// Para eso se suma a la posicion de la Roca el desplazamiento que
				// quiere hacer Rockford. Asi se obtiene el lugar que ocupa la roca
				// si esta se desplaza
				Roca roca = (Roca) entTarget;
				
				if (dir == Direccion.IZQUIERDA || dir == Direccion.DERECHA) {
					
					if (juego.getEntidad(Posicion.sumar(roca.getPosicion(), dir)).esVacio()) {
						// Si la roca se puede mover en la direccion especificada, se mueve
						// tanto a la roca como a Rockford
						roca.mover(dir);
						this.mover(dir);
					}
					
				}
			} else if (entTarget instanceof Salida) {
				// TODO hacer el conteo de diamantes, proabablemente lo tenga que
				// hacer el win, cuando vuelve borrarlos
				
				juego.eliminarEntidad(this);
				this.posicion = null;
				this.vivo = false;
				juego.win();
			}
			
		}

		// Si no se cumple ninguna de las condiciones anteriores, no hace nada
		// ya que no se puede mover
		
		

		// Si hizo un movimiento en el turno, reinicia el tiempo inactivo, si
		// no, aumenta la cantidad de turnos inactivo
		turnosInactivo = (dir != Direccion.NULA) ? 0 : turnosInactivo + 1;
		// Guarda el ultimo movimiento que hizo
		ultimoMovimiento = dir;
	}
	
	/**
	 * 
	 * Metodo utilizado cada vez que arranca el nivel, se posiciona a Rockford
	 * en la posicion recibida por parametro. Usualmente se recibe la posicion
	 * de la puerta de entrada
	 * 
	 * @param pos Posicion a donde se va a crear a Rockford
	 */
	public void spawn(Posicion pos) {
		vivo = true;
		diamantes = 0;
		posicion = new Posicion(pos.getX(), pos.getY());
		Juego.getInstance().agregarEntidad(this);
		
		turnosInactivo = 0;
		ultimoMovimiento = Direccion.NULA;
	}
	
	/**
	 * 
	 * Elimina a Rockford del mapa, y setea la variable vivo como false. Crea
	 * las explosiones alrededor de la posicion donde estaba. Le elimina todos
	 * los diamantes que consiguio hasta el momento.
	 * 
	 */
	public void kill() {
		if (vivo) {
			// Variables auxiliares
			int i, j, x, y;
			Juego juego = Juego.getInstance(); // Objeto juego para referenciarlo mas facil
			Posicion centro = this.getPosicion(); // Centro de la explosion
			
			if (MORTAL) {
				// Borra la posicion
				juego.eliminarEntidad(this);
				
				this.borrarPosicion();
				
				// Le resta una vida
				this.vidas--;
				
				// Lo setea como muerto
				this.vivo = false;
				
				// Le avisa al juego que murio
				juego.murioRockford();
				
				// Le saca todos los diamantes que consiguio
				this.diamantes = 0;
			}
			
			// Agrega todas las explosiones
			for (i = -1; i <= 1; i++) {
				for (j = -1; j <= 1; j++) {
					x = centro.getX() + i;
					y = centro.getY() + j;
					
					if (juego.esValida(x, y) &&
							juego.getEntidad(x, y).esExplotable())
						juego.agregarEntidad(new Explosion(x, y));
				}
			}
		}
	}
	
	/**
	 * Reinicia los valores de Rockford, como la vida y la cantidad de diamentes
	 * que tiene
	 */
	public void reiniciar() {
		this.vidas = Juego.MAX_VIDAS;
		this.vivo = false;
		this.diamantes = 0;
		this.borrarPosicion();
	}
	
	@Override
	public boolean esRockford() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Rockford [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		
		TipoEntidad ret = TipoEntidad.ROCKFORD_NORMAL;
		
		// Si hizo un movimiento en el ultimo turno, le pide el ultimo
		// movimiento que hizo
		if (turnosInactivo <= TURNOS_INACTIVO) {
			switch (ultimoMovimiento) {
			case ARRIBA:
				ret = TipoEntidad.ROCKFORD_ARRIBA;
				break;
				
			case ABAJO:
				ret = TipoEntidad.ROCKFORD_ABAJO;
				break;
				
			case IZQUIERDA:
				ret = TipoEntidad.ROCKFORD_IZQUIERDA;
				break;
				
			case DERECHA:
				ret = TipoEntidad.ROCKFORD_DERECHA;
				break;
				
			case NULA:
				ret = TipoEntidad.ROCKFORD_NORMAL;
				break;
			}
		} else
			ret = TipoEntidad.ROCKFORD_ESPERANDO;
		
		return ret;
	} 
}
