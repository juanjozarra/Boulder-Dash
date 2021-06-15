package boulderDash.controlador;

import java.util.Timer;

import boulderDash.Juego;
import boulderDash.complementos.Direccion;
import boulderDash.complementos.GameOverException;
import boulderDash.grafica.Grafica;

/**
 * 
 * Clase que contiene el main y llama al metodo principal del juego
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Controlador {
	
	private static Direccion teclaPresionada = Direccion.NULA;
	private static Direccion siguienteMovimiento = Direccion.NULA;
	
	private static final int TURN_DELAY = 90;
	private static Timer timer;
	
	public static void iniciaJuego(int nivel) {
		timer = new Timer();

		try {
			// Le avisa al modelo que inicie el juego
			Juego.getInstance().iniciarJuego(nivel);
			
			// Le avisa a la grafica que arranco el juego
			Grafica.getInstance().modoJuego();
		} catch (Exception e) {
			e.printStackTrace();
			kill();
		} catch (GameOverException e) {
			gameOver();
		}
		
		timer.schedule(new Tarea(), 10, TURN_DELAY);
	}
	
	public static void gameOver() {
		// Cancela el timer, por seguridad lo hace dos veces
		timer.cancel();
		Grafica.getInstance().gameOver();
	}
	
	public static void kill() {
		System.exit(666);
	}
	
	public static void main(String[] args) throws Exception {
		Grafica grafica = Grafica.getInstance();
		
		grafica.iniciar();
	}
	
	/**
	 * Setea cual va a ser el proximo movimiento de Rockford
	 * 
	 * @param dir Direccion en la que se realiza el movimiento
	 */
	public static void setSiguienteMovimiento(Direccion dir) {
		siguienteMovimiento = dir;
	}
	
	/**
	 * Setea el movimiento al cual esta relacionado la tecla presionada
	 * 
	 * @param dir Direccion relacionado a la tecla presionada
	 */
	public static void setTeclaPresionada(Direccion dir) {
		teclaPresionada = dir;
	}
	
	/**
	 * Devuelve cual es el proximo movimiento de Rockford
	 * 
	 * @return
	 */
	public static Direccion getProximoMovimiento() {
		if (siguienteMovimiento == Direccion.NULA)
			return teclaPresionada;
		else {
			Direccion aux;
			
			switch (siguienteMovimiento) {
				case ARRIBA:
					aux = Direccion.ARRIBA;
					break;
				case ABAJO:
					aux = Direccion.ABAJO;
					break;
				case IZQUIERDA:
					aux = Direccion.IZQUIERDA;
					break;
				case DERECHA:
					aux = Direccion.DERECHA;
					break;
				default:
					aux = Direccion.NULA;
					break;
			}
			
			siguienteMovimiento = Direccion.NULA;
			return aux;
		}
	}
}
