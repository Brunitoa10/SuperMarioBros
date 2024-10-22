package Vista.GestorPantalla;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;
import Vista.Paneles.PanelPantallaModoJuego;

public class ComandoCrearPanelModoJuego implements ComandoCrearPanel{

	@Override
	public JPanel ejecutar(ControladorVista controlador) {
		return new PanelPantallaModoJuego(controlador);
	}

}
