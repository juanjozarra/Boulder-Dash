package boulderDash.entidades;

import boulderDash.Juego;
import boulderDash.controlador.TipoEntidad;
import boulderDash.interfaces.Comportable;

public class Explosion extends EntidadInmovil implements Comportable {
	
	private int turnosLeft;
	
	// Constructor ========================================================
	
	public Explosion(int x, int y) {
		super(x, y);
		turnosLeft = 5;
	}
	
	@Override
	public void comportamiento() {
		if (turnosLeft <= 0)
			Juego.getInstance().eliminarEntidad(this);
		else
			turnosLeft--;
	}
	
	@Override
	public boolean esExplotable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Explosion [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.EXPLOSION;
	}
	
	
}
