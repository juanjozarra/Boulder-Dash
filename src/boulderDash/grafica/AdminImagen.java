package boulderDash.grafica;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import boulderDash.complementos.ImageNotFoundException;
import boulderDash.controlador.TipoEntidad;

public class AdminImagen {
	
	private Map<TipoEntidad, ImageIcon> mapa;
	
	private static AdminImagen instancia = null;
	
	private AdminImagen() {
		mapa = new HashMap<TipoEntidad, ImageIcon>();
		
		mapa.put(TipoEntidad.VACIO, convertirAImageIcon("img/vacio.jpg"));
		mapa.put(TipoEntidad.BASURA, convertirAImageIcon("img/basura.jpg"));
		mapa.put(TipoEntidad.ROCA, convertirAImageIcon("img/roca.gif"));
		mapa.put(TipoEntidad.DIAMANTE, convertirAImageIcon("img/diamante.gif"));
		mapa.put(TipoEntidad.MARIPOSA, convertirAImageIcon("img/mariposa.gif"));
		mapa.put(TipoEntidad.EXPLOSION, convertirAImageIcon("img/explosion.gif"));
		mapa.put(TipoEntidad.LUCIERNAGA, convertirAImageIcon("img/luciernaga.gif"));
		mapa.put(TipoEntidad.SALIDA, convertirAImageIcon("img/puerta.gif"));
		mapa.put(TipoEntidad.ENTRADA, convertirAImageIcon("img/puerta.gif"));
		
		mapa.put(TipoEntidad.NULO, convertirAImageIcon("img/error.jpg"));
		
		// Todo Rockford
		mapa.put(TipoEntidad.ROCKFORD_NORMAL, convertirAImageIcon("img/rockfordnormal.jpg"));
		mapa.put(TipoEntidad.ROCKFORD_ARRIBA, convertirAImageIcon("img/rockfordsubiendo.gif"));
		mapa.put(TipoEntidad.ROCKFORD_ABAJO, convertirAImageIcon("img/rockfordbajando.gif"));
		mapa.put(TipoEntidad.ROCKFORD_IZQUIERDA, convertirAImageIcon("img/rockfordizquierda.gif"));
		mapa.put(TipoEntidad.ROCKFORD_DERECHA, convertirAImageIcon("img/rockfordderecha.gif"));
		mapa.put(TipoEntidad.ROCKFORD_ESPERANDO, convertirAImageIcon("img/rockfordesperando.gif"));
		
		// Muros
		mapa.put(TipoEntidad.MURO_NORMAL, convertirAImageIcon("img/muro_normal.jpg"));
		mapa.put(TipoEntidad.MURO_IRROMPIBLE, convertirAImageIcon("img/muro_irrompible.jpg"));
		
	}
	
	public static AdminImagen getInstance() {
		if (instancia == null)
			instancia = new AdminImagen();
		return instancia;
	}
	
	private ImageIcon convertirAImageIcon(String path) {
		return new ImageIcon(getClass().getResource(path));
	}
	
	/**
	 * Devuelve la imagen (Image) relacionada al TipoEntidad solicitado
	 * 
	 * @param e TipoEntidad del cual se quiere la imagen
	 * @return Image del TipoEntidad
	 */
	public Image getImage(TipoEntidad e) throws ImageNotFoundException{
		if (mapa.containsKey(e))
			return mapa.get(e).getImage();
		else
			throw new ImageNotFoundException();
	}
	
	/**
	 * Devuelve la imagen (ImageIcon) relacionada al TipoEntidad solicitado
	 * 
	 * @param e TipoEntidad del cual se quiere la imagen
	 * @return ImageIcon del TipoEntidad
	 * @throws ImageNotFoundException 
	 */
	public ImageIcon getImageIcon(TipoEntidad e) throws ImageNotFoundException {
		if (mapa.containsKey(e))
			return mapa.get(e);
		else
			throw new ImageNotFoundException();
	}
	
	/**
	 * Devuelve el sprite de error
	 * 
	 * @return Sprite de error, en ImageIcon
	 */
	public ImageIcon getErrorImageIcon() {
		return mapa.get(TipoEntidad.NULO);
	}
	
	/**
	 * Devuelve el sprite de error
	 * 
	 * @return Sprite de error, en Image
	 */
	public Image getErrorImage() {
		return mapa.get(TipoEntidad.NULO).getImage();
	}
}
