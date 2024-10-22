package Vista.GestorPantalla;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;
import Vista.Paneles.PanelPantallaPrincipal;

public class ComandoCrearPanelPrincipal implements ComandoCrearPanel{

	@Override
	public JPanel ejecutar(ControladorVista controlador) {
		return new PanelPantallaPrincipal(controlador, controlador.obtenerModoJuego());
	}

}
