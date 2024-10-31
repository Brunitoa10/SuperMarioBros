package Vista;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaManager {
	protected JFrame ventana;

	public VentanaManager() {
		ventana = new JFrame("Super Mario Bros - Equipo Basados");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setIconImage(cargarIcono());
		ventana.setSize(Constantes.ConstantesVista.VENTANA_ANCHO, Constantes.ConstantesVista.VENTANA_ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public void setContenido(JPanel contenido) {
		ventana.setContentPane(contenido);
		refrescar();
	}

	private Image cargarIcono() {
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/imagenes/original/Mario.png"));
	}

	public void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}
}
