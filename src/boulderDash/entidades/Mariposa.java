package boulderDash.entidades;

import boulderDash.Juego;
import boulderDash.complementos.Estatico;
import boulderDash.complementos.MovimientoAntiHorario;
import boulderDash.controlador.TipoEntidad;

public class Mariposa extends EntidadExplosiva {
	
	// Constructor ==============================
	
	public Mariposa(int x, int y) {
		super(x, y);
		ordenMov = new MovimientoAntiHorario();
	}
	
	/**
	 * 
	 * Explota, en un cuadrado de 3x3, dejando diamantes
	 * 
	 */
	@Override
	public void kill() {
		// Variables auxiliares
		int i, j;
		int centerAbsolutX = this.getPosicion().getX();
		int centerAbsolutY = this.getPosicion().getY();
		Juego juego = Juego.getInstance();
		
		// Recorre un cuadrado de 3x3, con centro en la posicion de la entidad
		// y radio de 1.
		for (i = -1; i <= 1; i++) {
			for (j = -1; j <= 1; j++) {
				/*
				 *  Si la posicion es explotable (todo menos muro irrompible y
				 *  la salida). Crea una explosion en los vacios, crea diamante
				 *  en el resto, y si es rockford, lo mata.
				 */
				Entidad aux =juego.getEntidad(centerAbsolutX + i, centerAbsolutY + j);
				
				if (aux.esExplotable()) {
					if (juego.getEntidad(centerAbsolutX + i, centerAbsolutY + j).esRockford())
						Rockford.getInstance().kill();
					else if (aux.esVacio())
						juego.agregarEntidad(new Explosion(centerAbsolutX + i, centerAbsolutY + j));
					else
						juego.agregarEntidad(new Diamante(centerAbsolutX + i, centerAbsolutY + j, new Estatico()));
						
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Mariposa [" + this.getID() + "]";
	}

	@Override
	public TipoEntidad getTipo() {
		return TipoEntidad.MARIPOSA;
	}

}
