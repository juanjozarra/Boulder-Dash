package boulderDash;

import java.util.LinkedList;

import boulderDash.complementos.*;
import boulderDash.controlador.TipoEntidad;
import boulderDash.entidades.*;
import boulderDash.interfaces.Comportable;
import boulderDash.levelReader.BDLevelReader;

/**
 * 
 * Clase principal, Singleton, contiene el mapa y el metodo principal.
 * 
 * Es la principal via de comunicacion entre objetos
 * 
 * @author Paradiso - Zarragoicoechea
 *
 */

public class Juego {
	
	
	/*
	 * TODO Justificar porque Mapa es interna
	 * 
	 */
	private class Mapa {

		// Atributos ================================
		
		private int ancho;
		private int alto;
		
		private Entidad[][] mapa;
		
		// Constructor ==============================
		
		public Mapa(int x, int y) {
			mapa = new Entidad[x][y];
			ancho = x;
			alto = y;
		}
		
		// Metodos Simples ==========================
		
		public int getMaxX() {
			return ancho;
		}
		
		public int getMaxY() {
			return alto;
		}
		
		/**
		 * 
		 * Devuelve la entidad que hay en una posicion determinada
		 * 
		 * @param x coordenada en X
		 * @param y coordenada en Y
		 * @return la entidad en la posicion (x,y)
		 */
		public Entidad getEntidad(int x, int y) {
			return mapa[x][y];
		}
		
		// Metodos complejos ========================
		
		/**
		 * 
		 * Agrega una entidad al mapa, las coordenadas las saca de la entidad misma
		 * 
		 * @param entidad a agregar
		 */
		public void agregarEntidad(Entidad entidad) {
			// Se obtiene la posicion de la nueva entidas
			int x = entidad.getPosicion().getX();
			int y = entidad.getPosicion().getY();
			
			// Y se agrega al mapa
			mapa[x][y] = entidad;
		}
		
		/**
		 * Agrega una entidad en una posicion determinada
		 * 
		 * @param e Entidad a agregar
		 * @param x Coordenada X donde se quiere agregar
		 * @param y Coordenada Y donde se quiere agregar
		 */
		public void agregarEntidad(Entidad e, int x, int y) {
			mapa[x][y] = e;
		}
		
		/**
		 * Recibe una posicion y elimina la entidad que haya en la misma
		 * 
		 * @param posicion Posicion donde se encuentra la entidad a eliminar
		 */
		public void eliminarEntidad(Posicion posicion) {
			// Se obtienen los valores de x e y
			int x = posicion.getX();
			int y = posicion.getY();
			
			// Se reemplaza la posicion por vacio
			mapa[x][y] = new Vacio(x, y);
		}
		
		/**
		 * 
		 * Metodo identico a eliminarEntidad(Posicion), salvo que recibe una entidad
		 * en vez de su posicion
		 * 
		 * @param entidad Entidad que se quiere eliminar
		 */
		public void eliminarEntidad(Entidad entidad) {
			eliminarEntidad(entidad.getPosicion());
		}
	}
	
	// Constantes del juego, como la vida de Rockford
	public static final int MAX_VIDAS = 4;
	public static final String PATH_NIVELES = "levels.xml";
	
	// Atributos ================================
	
	private static Juego instancia = null;
	
	// Primitivos
	
	private boolean pausa;

	private long tiempo;
	private int tiempoGeneral;
	private long millisUltimaUpdate;
	
	private int turno;
	private int puntuacionGeneral;
	private int puntuacionNivel;
	
	// Numero de turnos desde que muere Rockford hasta que se lanza la excepcion
	private long muerteTimeout;
	
	// Auxiliriaes
	private boolean win;
	private boolean murioRockford;
	
	// Objetos
	private Mapa mapa; // Mapa/Tablero
	
	private InfoNivel infoNivel; // Informacion sobre el nivel
	
	private LinkedList<Comportable> entidadesComportables; // Lista de entidades que tienen
											  // comportamiento a ejecutar
	
	// Lector de niveles proporcionado por la catedra
	private BDLevelReader levelReader;
	private long winTimeout;
	private int cantNiveles;
	
	
	// Constructor y Singleton ================================================
			
	private Juego(){
	}
	
	public static Juego getInstance(){
		if (instancia == null)
			instancia = new Juego();
		return instancia;
	}

	// Metodos ================================================================
	/**
	 * 
	 * Metodo que agrega una entidad al juego, si es Comportable tambien la
	 * agrega a la lista de entidades comportables.
	 * 
	 * @param entidad Entidad a ser agregada
	 */
	public void agregarEntidad(Entidad entidad) {
		
		// Elimina la entidad que habia antes en el lugar
		if (getEntidad(entidad.getPosicion()) != null)
			eliminarEntidad(getEntidad(entidad.getPosicion()));
		
		// Agrega la entidad al mapa
		mapa.agregarEntidad(entidad);
		
		// Y si es comportable la agrega a la lista de comportables
		if (entidad instanceof Comportable)
			entidadesComportables.add( (Comportable) entidad);
		else if (entidad.esRockford())
			murioRockford = false;
	}
	
	/**
	 * 
	 * Elimina una entidad del mapa, si es comportable lo borra tambien de
	 * la lista de comportables
	 * 
	 * @param entidad a ser eliminada
	 */
	public void eliminarEntidad(Entidad entidad) {
		mapa.eliminarEntidad(entidad);
		
		if (entidad instanceof Comportable) {
			Comportable aux = (Comportable) entidad;
			entidadesComportables.remove(aux);
		}
	}
	
	/**
	 * 
	 * Metodo utilizado por Rockford para avisar que llego a la puerta de salida.
	 * Elimina al rockford del mapa y setea la variable win a true. Suma la puntuacion
	 * 
	 */
	public void win() {
		win = true;
		
		// Suma a la puntuacion la obtenida en el nivel y los segundo que le
		// quedaron
		puntuacionGeneral += puntuacionNivel + tiempo / 1000;
		
		// Guarda el tiempo que paso el nivel, que es el tiempo total
		// menos el tiempo restante
		tiempoGeneral += infoNivel.getTiempo() - tiempo / 1000;
	}
	
	/**
	 * Inicia el juego, reinicia el puntaje, reinicia a rockford
	 * @throws GameOverException 
	 * 
	 */
	public void iniciarJuego(int nivel) throws Exception, GameOverException {
		// Crea las variables minimas necesarias:
		// Lector de niveles y Mapa
		levelReader = new BDLevelReader();
		cantNiveles = levelReader.readLevels(PATH_NIVELES);
		
		mapa = new Mapa(levelReader.getWIDTH(), levelReader.getHEIGHT());
		
		this.puntuacionGeneral = 0;
		this.tiempoGeneral = 0;
		Rockford.getInstance().reiniciar();
		
		cargarNivel(nivel);
	}

	/**
	 * Carga el nivel pasado por parametro
	 * 
	 * @param nivel Nivel que se quiere cargar
	 * @throws GameOverException Cuando trata de cargar el nivel 11
	 */
	private void cargarNivel(int nivel) throws Exception, GameOverException {
		
		if (nivel > cantNiveles)
			throw new GameOverException();
		
		// Variables x, y auxiliares para cargar el mapa
		int x, y;
		
		// Objeto que contiene la informacion del nivel
		infoNivel = new InfoNivel();
		
		// Lista de todas las entidades que contienen comportamiento
		entidadesComportables = new LinkedList<Comportable>();
		
		// Guarda el nivel elegido en el objeto que tiene la informacion del
		// nivel y carga toda la informacion del mismo
		infoNivel.setNivel(nivel);
		
		// Le dice al reader que cargue un nivel
		levelReader.setCurrentLevel(infoNivel.getNivel());
		// Carga el tiempo limite del nivel
		tiempo = infoNivel.getTiempo() * 1000;
		// Carga el nivel en el mapa/traduce los mapas
		for ( y = 0; y < mapa.getMaxY(); y++ ) {
			for ( x = 0; x < mapa.getMaxX(); x++ ) {
				
				switch (levelReader.getTile(x, y)) {
				
					case EMPTY:
						this.agregarEntidad(new Vacio(x, y));	// Agrega el pj al mapa
						break;
						
					case DIRT:
						this.agregarEntidad(new Basura(x, y));
						break;
						
					case TITANIUM:
						this.agregarEntidad(new MuroIrrompible(x, y));
						break;
						
					case WALL:
						this.agregarEntidad(new MuroNormal(x, y));
						break;
						
					case ROCK:
						this.agregarEntidad(new Roca(x, y, new Estatico()));
						break;
						
					case FALLINGROCK:
						this.agregarEntidad(new Roca(x, y, new Cayendo()));
						break;
						
					case DIAMOND:
						this.agregarEntidad(new Diamante(x, y, new Estatico()));
						break;
						
					case FALLINGDIAMOND:
						this.agregarEntidad(new Diamante(x, y, new Cayendo()));
						break;
						
					case AMOEBA:
						this.agregarEntidad(new Ameba(x, y));
						break;
						
					case FIREFLY:
						this.agregarEntidad(new Luciernaga(x, y));
						break;
						
					case BUTTERFLY:
						this.agregarEntidad(new Mariposa(x, y));
						break;
						
					case EXIT:
						infoNivel.setSalida(new Posicion(x, y));
						this.agregarEntidad(new Basura(x, y));
						break;
						
					case PLAYER:
						infoNivel.setEntrada(new Posicion(x, y));
						this.agregarEntidad(new Entrada(x, y));
						break;
				}
			}
		}
		
		this.win = false;
		this.turno = 0;
		this.murioRockford = false;
		this.muerteTimeout = Long.MAX_VALUE;
		this.winTimeout = Long.MAX_VALUE;
		this.millisUltimaUpdate = System.currentTimeMillis();
		this.pausa = false;
		this.puntuacionNivel = 0;
	}
	
	/**
	 * 
	 * Ejecuta el siguiente turno, verifica si hay tiempo, si Rockford sigue
	 * vivo. Ejecuta primero el movimiento de Rockford y despues el resto
	 * 
	 * @param movRockford Movimiento que va a hacer Rockford
	 * @throws Exception Errores durante la carga del nivel
	 * @throws BoulderMechanicException Excepciones que pueden ocurrir durante
	 * el juego, ver la clase BoulderMechanicException para ver todas las que
	 * pueden ocurrir.
	 */
	public void siguienteTurno(Direccion movRockford)
	throws GameOverException, Exception {
		
		if (!pausa ) {
			if (Rockford.getInstance().estaVivo())
				Rockford.getInstance().comportamiento(movRockford);
			else if (murioRockford) {
				if (muerteTimeout == tiempo / 1000)
					if (Rockford.getInstance().getVidas() > 0) {
						// Si muere y le quedan vidas
						cargarNivel(infoNivel.getNivel());
					}
					else {
						// Si termina el juego, suma la puntuacion acumulada
						// hasta el momento en el nivel que murio
						
						// Suma a la puntuacion la obtenida en el nivel y los segundo que le
						// quedaron
						puntuacionGeneral += puntuacionNivel + tiempo / 1000;
						
						// Guarda el tiempo que paso el nivel, que es el tiempo total
						// menos el tiempo restante
						tiempoGeneral += infoNivel.getTiempo() - tiempo / 1000;
						
						throw new GameOverException();
					}
				else if (muerteTimeout == Long.MAX_VALUE)
					muerteTimeout = tiempo/1000 - 3;
			}

			int i = 0;
			// Ejecuta todos los comportamientos
			while (i < this.entidadesComportables.size()) {
				entidadesComportables.get(i).comportamiento();
				i++;
			}
			
			if (win)
				if (winTimeout == tiempo / 1000)
					// Si le quedan vidas, pregunta si gano (llego a la salida)
					cargarNivel(infoNivel.getNivel() + 1);
				else if (winTimeout == Long.MAX_VALUE)
					winTimeout = tiempo/1000 - 2;
			
			tiempo -= System.currentTimeMillis() - millisUltimaUpdate;
			millisUltimaUpdate = System.currentTimeMillis();
			
			if (tiempo <= 0 && Rockford.getInstance().estaVivo()) {
				Rockford.getInstance().kill();
			}
			
			turno++;
		}
	}
	

	// Metodos relacionados con el mapa
	
	/**
	 * Devuelve el valor maximo de la coordenada X, que es el ancho del mapa
	 * 
	 * @return Valor maximo de X (ancho - 1)
	 */
	public int getMaxX() {
		return mapa.getMaxX();
	}
	
	/**
	 * Devuelve el valor maximo de la coordenada Y, que es el alto del mapa - 1
	 * 
	 * @return Valor maximo de Y (alto - 1)
	 */
	public int getMaxY() {
		return mapa.getMaxY();
	}
	
	/**
	 * Devuelve la entidad que hay en una posicion determinada
	 * 
	 * @param pos Posicion de donde se quiere obtener la entidad
	 * @return La entidad que se encuentra en esa posicion
	 */
	public Entidad getEntidad(Posicion pos) {
		return mapa.getEntidad(pos.getX(), pos.getY());
	}
	
	/**
	 * 
	 * Devuelve la entidad que hay en una posicion determinada
	 * 
	 * @param x Coordenada X de la posicion
	 * @param y Coordenada Y de la posicion
	 * @return Devuelve la entidad que hay en la posicion solicitada
	 */
	public Entidad getEntidad(int x, int y) {
		return mapa.getEntidad(x, y);
	}
	
	/**
	 * Mueve una entidad a una posicion nueva, no verifica que hay en la
	 * posicion a donde se va a mover y deja un vacio en la anterior.
	 * 
	 * @param e Entidad a mover
	 * @param x Coordenada X destino
	 * @param y Coordenada Y Destino
	 */
	public void mover(Entidad e, int x, int y) {
		// Libera el lugar de la posicion vieja
		mapa.eliminarEntidad(e.getPosicion());
		
		// Asigna en la posicion nueva el objeto que se quier mover
		mapa.agregarEntidad(e, x, y);
	}
	
	/**
	 * 
	 * Mueve una entidad a una posicion nueva, no verifica que hay en la
	 * posicion a donde se va a mover, y deja un vacio en donde estaba.
	 * 
	 * @param e Entidad a mover
	 * @param pos Posicion nueva
	 */
	public void mover(Entidad e, Posicion pos) {
		mover(e, pos.getX(), pos.getY());
	}
	
	/**
	 * Devuelve la cantidad de diamantes minimos necesarios para pasar el nivel
	 * 
	 * @return Cantidad de diamantes
	 */
	public int getDiamantesMin() {
		return infoNivel.getDiamanteMin();
	}
	
	/**
	 * Verifica si Rockford obtuvo los diamantes, y si los obtuvo habilita la
	 * puerta de salida
	 * 
	 */
	public void obtuvoDiamantes() {
		Rockford rf = Rockford.getInstance();
		
		if (rf.getDiamantes() >= infoNivel.getDiamanteMin()) {
			
			// Si Rockford tiene la cantidad de diamantes necesarios, agrega la salida al Mapa
			mapa.eliminarEntidad(infoNivel.getSalida());
			this.agregarEntidad(new Salida(infoNivel.getSalida().getX(),
					infoNivel.getSalida().getY()));
			
		}
		
		// Suma el puntaje correspondiente
		if (rf.getDiamantes() <= infoNivel.getDiamanteMin())
			puntuacionNivel += infoNivel.getValorDiamante();
		else
			puntuacionNivel += infoNivel.getValorDiamanteBonus();
	}
	
	
	/**
	 * Devuelve una version reducida del mapa, que contiene TipoEntidad. Usada
	 * por la grafica para saber que hay en cada posicion
	 * 
	 * @return Matriz de dos dimensiones, de TipoEntidad
	 */
	public TipoEntidad[][] getMapa() {
		TipoEntidad[][] mapaAux = new TipoEntidad[mapa.getMaxX()][mapa.getMaxY()];
		
		for (int y = 0; y < mapa.getMaxY(); y++) {
			for (int x = 0; x < mapa.getMaxX(); x++) {
				mapaAux[x][y] = mapa.getEntidad(x, y).getTipo();
			}
		}
		
		return mapaAux;
	}
	
	/**
	 * Devuelve la informacion del nivel (es una copia del original, para que
	 * no se modifiquen valores)
	 * 
	 * @return Objeto InfoNivel que contiene la informacion del nivel actual
	 */
	public InfoNivel getInfoNivel() {
		return infoNivel.copy();
	}

	/**
	 * Devuelve el numero de turno, que es la cantidad de turnos que pasaron
	 * 
	 * @return Numero de turno
	 */
	public int getTurno() {
		return turno;
	}
	
	/**
	 * Devuelve el tiempo restante que le queda al nivel
	 * 
	 * @return tiempo restante
	 */
	public int getTiempo() {
		int aux = (int) tiempo / 1000;
		return aux >= 0 ? aux : 0;
	}
	
	/**
	 * Pausa la ejecucion del juego, es decir, no se ejecuta ningun
	 * comportamiento hasta que se vuelve a hacer togglePausa();
	 * 
	 */
	public void togglePausa() {
		pausa = !pausa;
		millisUltimaUpdate = System.currentTimeMillis();
	}
	
	/**
	 * Devuelve la puntuacion actual que lleva Rockford asegurada
	 * 
	 * @return
	 */
	public int getPuntuacionGeneral() {
		return puntuacionGeneral;
	}
	
	/**
	 * Devuelve la puntuacion que Rockford acumulo en el nivel
	 * 
	 * @return
	 */
	public int getPuntuacionNivel() {
		return puntuacionNivel;
	}
	
	
	
	/**
	 * Metodo usado por Rockford para avisar que murio
	 * 
	 */
	public void murioRockford() {
		murioRockford = true;
	}

	/**
	 * Devuelve si el juego esta pausado o no
	 * 
	 * @return True si esta pausado, False caso cont
	 */
	public boolean pausado() {
		return pausa;
	}

	/**
	 * Devuelve el tiempo total que lleva
	 * 
	 * @return tiempo total jugado
	 */
	public int getTiempoTotal() {
		return tiempoGeneral;
	}
	
	/**
	 * Devuelve la puntuacion de los de los diamantes que agarre Rockford.
	 * Si ya obtuvo los diamantes para habilitar la salida, devuelve el Bonus,
	 * si no deuvelve el normal
	 * 
	 * @return Valor del proximo diamante a obtener
	 */
	public int getValorDiamante() {
		if (Rockford.getInstance().getDiamantes() < infoNivel.getDiamanteMin())
			return infoNivel.getValorDiamante();
		else
			return infoNivel.getValorDiamanteBonus();
	}

	/**
	 * Pregunta si la posicion esta dentro de los limites de la matriz del juego
	 * 
	 * @param pos Posicion a verificar
	 * @return true si esta dentro de la matriz, false si no
	 */
	public boolean esValida(Posicion pos) {
		return esValida(pos.getX(), pos.getY());
 	}
	
	/**
	 * Pregunta si la posicion esta dentro de los limites de la matriz del juego
	 * 
	 * @param x Coordenada X a verificar
	 * @param y Coordenada Y a verificar
	 * @return true si es valida, false si no
	 */
	public boolean esValida(int x, int y) {
		
		if ((0 <= x && x < getMaxX()) && (0 <= y && y < getMaxY()))
			return true;
		else
			return false;
		
	}
}

