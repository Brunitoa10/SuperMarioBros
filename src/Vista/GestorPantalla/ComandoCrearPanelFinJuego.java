package Vista.GestorPantalla;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;
import Vista.Paneles.PanelPantallaFinJuego;

public class ComandoCrearPanelFinJuego implements ComandoCrearPanel{

	@Override
	public JPanel ejecutar(ControladorVista controlador) {
		return new PanelPantallaFinJuego(controlador, controlador.obtenerModoJuego());
	}

}
