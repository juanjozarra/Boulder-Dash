package boulderDash.entidades;

import boulderDash.complementos.MovimientoHorario;
import boulderDash.controlador.TipoEntidad;

public class Luciernaga extends EntidadExplosiva{
	
	// Constructor ==============================
	
	public Luciernaga(int x, int y) {
		super(x, y);
		ordenMov = new MovimientoHorario();
	}
	
	@Override
	public String toString() {
		return "Luciernaga [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.LUCIERNAGA;
	}
}
