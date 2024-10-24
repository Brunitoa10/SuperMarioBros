package Vista.Controladores;

import GestorArchivos.Ranking;
import Logica.Juego;

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

    public void agregarJugadorAlRanking(String nombre, int puntaje);

	public void setNombreUsuario(String nombreUsuario);

	public void mostrarPantallaModoJuego();

	public String obtenerNombreUsuario();
	
	public void mostrarPantallaNombreUsuario();

	public int obtenerPuntajeJugador();
}
