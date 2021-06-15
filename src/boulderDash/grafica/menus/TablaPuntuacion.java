package boulderDash.grafica.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import boulderDash.grafica.AdminFont;
import boulderDash.grafica.Boton;
import boulderDash.grafica.EntradaTabla;
import boulderDash.grafica.Grafica;
import boulderDash.grafica.PanelPrincipal;

public class TablaPuntuacion extends PanelPrincipal {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane panelTabla;
	private JTable tabla;
	private Boton volver;
	
	public TablaPuntuacion(final MenuPrincipal menu, int max) {
		
		// Actualiza la lista que tiene las entradas
		Grafica.getInstance().ordenarTabla();
		
		String[] header = {"#", "NOMBRE", "PUNTOS", "TIEMPO"};
		
		Object[][] entradas = new Object[max][4];
		
		
		// Lee toda la informacion el archivo
		List<EntradaTabla> entradasLeidas = Grafica.getInstance().getEntradas();
		
		if (entradasLeidas.isEmpty()) {
			entradas[0][0] = "";
			entradas[0][1] = "NO HAY ENTRADAS";
			entradas[0][2] = "";
			entradas[0][3] = "";
		} else {
			// Carga todas las entradas necesarias en el arreglo
			EntradaTabla e = null;
			for (int i = 0; i < entradasLeidas.size() && i < max; i++) {
				e = entradasLeidas.get(i);
				entradas[i][0] = i + 1;
				entradas[i][1] = e.getNombre();
				entradas[i][2] = e.getScore();
				entradas[i][3] = e.getTime();
			}
		}
			
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(entradas, header) {
			
			private static final long serialVersionUID = 1L;

			// Hace que no se puedan modificar las celdas
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		// Estilo
		tabla.setRowHeight(40);
		tabla.setCellSelectionEnabled(false);
		tabla.setOpaque(false);
		tabla.setBorder(null);
		tabla.setBackground(new Color(65, 65, 254));
		tabla.setGridColor(new Color(65, 65, 254));
		tabla.setFont(AdminFont.getFont(20f));
		
		// Estilo del header
		tabla.getTableHeader().setBackground(new Color(65, 65, 254));
		tabla.getTableHeader().setForeground(Color.WHITE);
		tabla.getTableHeader().setFont(AdminFont.getFont(20f));
		tabla.getTableHeader().setPreferredSize(new Dimension(50, 50));
	
		// Estilo que tiene que manipularse celda por celda
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setBackground(new Color(65, 65, 254));
		render.setForeground(Color.WHITE);
		render.setHorizontalAlignment(SwingConstants.CENTER);
		render.setBorder(null);
		
		for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(render);
		}
		
		// Ancho de las columnas
		tabla.getColumnModel().getColumn(0).setMinWidth(45);
		tabla.getColumnModel().getColumn(0).setMaxWidth(45);
		
		tabla.getColumnModel().getColumn(2).setMinWidth(140);
		tabla.getColumnModel().getColumn(2).setMaxWidth(140);
		
		tabla.getColumnModel().getColumn(3).setMinWidth(140);
		tabla.getColumnModel().getColumn(3).setMaxWidth(140);
		
		// Panel que contiene la tabla y tiene el scroll
		panelTabla = new JScrollPane(tabla);
		panelTabla.setBounds(100, 50, 760, 376);
		panelTabla.getViewport().setBorder(null);
		panelTabla.getViewport().setBackground(new Color(65, 65, 254));
		panelTabla.setBorder(BorderFactory.createEmptyBorder());
		add(panelTabla);
		
		volver = new Boton("< VOLVER", 240, 470);
		volver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu.mostrarInicio();
			}
		});
		add(volver);
	}
}
