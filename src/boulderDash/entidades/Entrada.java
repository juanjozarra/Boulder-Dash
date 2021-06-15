package boulderDash.entidades;

import boulderDash.controlador.TipoEntidad;
import boulderDash.interfaces.Comportable;

public class Entrada extends EntidadInmovil implements Comportable {

	private int contador;
	
	public Entrada(int x, int y) {
		super(x, y);
		contador = 5;
	}
	
	/**
	 * 
	 * Al cabo de 5 turnos se auto-reemplaza por Rockford
	 * 
	 */
	@Override
	public void comportamiento() {
		if (contador <= 0) {
			Rockford.getInstance().spawn(this.getPosicion());
		} else
			contador--;
		
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.ENTRADA;
	}

}
