package Vista.Controladores;

import GestorArchivos.Ranking;
import Logica.Juego;
import Logica.OyenteTeclado;

public interface ControladorVista {
    public void accionarInicioJuego(String modoJuego);

    public void accionarPantallaModoJuego();

    public void accionarPantallaRanking();

    public void cambiarModoJuego(String modoJuego);

    public void mostrarPantallaInicial(String modoJuego);

    public void mostrarPantallaRanking();

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

    public OyenteTeclado obtenerOyente();

}
