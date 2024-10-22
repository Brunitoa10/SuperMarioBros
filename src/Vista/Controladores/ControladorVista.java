package Vista.Controladores;

import Logica.Juego;
import Logica.Ranking;

public interface ControladorVista {
    public void accionarInicioJuego(String modoJuego);

    public void accionarPantallaModoJuego();

    public void accionarPantallaRanking();

    public void cambiarModoJuego(String modoJuego);

    public void mostrarPantallaInicial(String modoJuego);

    public void mostrarPantallaRanking();

    public void refrescar();

    public Juego getJuego();
  
    public String obtenerModoJuego();

    public void mostrarPantallaFinJuego();

	public void volverAlPanelAnterior();
	
	public Ranking obtenerRanking();
}
