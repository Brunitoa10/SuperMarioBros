package Vista.GestorPantalla;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;
import Vista.Paneles.PanelPantallaRanking;

public class ComandoCrearPanelRanking implements ComandoCrearPanel{

	@Override
	public JPanel ejecutar(ControladorVista controlador) {
		return new PanelPantallaRanking(controlador, controlador.obtenerRanking());
	}

}
