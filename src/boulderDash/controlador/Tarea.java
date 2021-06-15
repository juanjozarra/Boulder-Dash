package boulderDash.controlador;

import java.io.IOException;
import java.util.TimerTask;

import boulderDash.Juego;
import boulderDash.complementos.GameOverException;
import boulderDash.grafica.Grafica;

public class Tarea extends TimerTask {
	
	@Override
	public void run() {
		Juego juego = Juego.getInstance();
		Grafica grafica = Grafica.getInstance();
		
		try {
			// Le dice al modelo que ejecute todos los comportamientos
			juego.siguienteTurno(Controlador.getProximoMovimiento());
			
			// Le ordena a la grafica que actualice la vista
			grafica.actualizar();
		} catch (GameOverException e) {
			// Si se queda sin vidas, cancela el timer y avisa a al controlador
			this.cancel();
			Controlador.gameOver();
		} catch (IOException e) {
			this.cancel();
			Controlador.gameOver();
		} catch (Exception e) {
			e.printStackTrace();
			this.cancel();
			Controlador.gameOver();
		}
		
	}

}
