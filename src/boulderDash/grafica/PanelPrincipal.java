package boulderDash.grafica;

import java.awt.Dimension;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public PanelPrincipal() {

		this.setMinimumSize(new Dimension(960, 576));
		this.setPreferredSize(new Dimension(960, 576));
		this.setMaximumSize(new Dimension(960, 576));
		setBounds(0, 0, 960, 576);
		setBorder(null);
		setOpaque(false);
		this.setLayout(null);
	}

}
