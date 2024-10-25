package Vista.GestorPantalla;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;
import Vista.Paneles.PanelPantallaNivel;

public class ComandoCrearPanelNivel implements ComandoCrearPanel{

	@Override
	public JPanel ejecutar(ControladorVista controlador) {
		return new PanelPantallaNivel(controlador);
	}

}