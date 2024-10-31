package Vista.Paneles;

import GestorArchivos.Ranking;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;

public class PanelFactory {
	
	protected ControladorVista controladorVista;
	protected ControladorVistaJuego controladorVistaJuego;

	public PanelFactory(ControladorVista controladorVista, ControladorVistaJuego controladorVistaJuego) {
		this.controladorVista = controladorVista;
		this.controladorVistaJuego = controladorVistaJuego;
	}

	public PanelPantallaNivel crearPanelPantallaNivel() {
		return new PanelPantallaNivel(controladorVistaJuego);
	}

	public PanelPantallaPrincipal crearPanelPantallaPrincipal(String modoJuego) {
		return new PanelPantallaPrincipal(controladorVista, modoJuego);
	}

	public PanelPantallaPerdiste crearPanelPantallaPerdiste(String modoJuego) {
		return new PanelPantallaPerdiste(controladorVista, modoJuego);
	}

	public PanelPantallaRanking crearPanelPantallaRanking(Ranking ranking) {
		return new PanelPantallaRanking(controladorVista, ranking);
	}

	public PanelPantallaModoJuego crearPanelPantallaModoJuego() {
		return new PanelPantallaModoJuego(controladorVista);
	}

	public PanelPantallaNombreUsuario crearPanelPantallaNombreUsuario() {
		return new PanelPantallaNombreUsuario(controladorVistaJuego);
	}

	public PanelPantallaCarga crearPanelPantallaCarga() {
		return new PanelPantallaCarga();
	}

	public PanelPantallaVictoria crearPanelPantallaVictoria(String modoJuego) {
		return new PanelPantallaVictoria(controladorVista, modoJuego);
	}
}
