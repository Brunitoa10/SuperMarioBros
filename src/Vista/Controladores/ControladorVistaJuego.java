package Vista.Controladores;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.Juego;
import Vista.ObserverGrafica.Observer;

public interface ControladorVistaJuego {

    /**
     * Registra una entidad lógica en la vista.
     * @param entidadLogica La entidad lógica a registrar.
     * @return Un objeto Observer que observa la entidad registrada.
     */
    public Observer registrarEntidad(EntidadLogica entidadLogica);

    /**
     * Registra un jugador en la vista.
     * @param entidadJugador La entidad jugador a registrar.
     * @return Un objeto Observer que observa al jugador registrado.
     */
    public Observer registrarEntidad(EntidadJugador entidadJugador);

    /**
     * Muestra la pantalla del nivel actual del juego.
     */
    public void mostrarPantallaNivel();

    /**
     * Muestra la pantalla al final del juego (derrota).
     * @param modoJuego El modo de juego que se estaba jugando.
     */
    public void mostrarPantallaFinJuego(String modoJuego);
    
    /**
     * Muestra la pantalla de victoria.
     * @param modoJuego El modo de juego que se estaba jugando.
     */
    public void mostrarPantallaVictoria(String modoJuego);

    /**
     * Muestra la pantalla de ranking desde la vista de juego.
     */
    public void mostrarPantallaRanking();

    /**
     * Refresca la vista del juego.
     */
    public void refrescar();

    /**
     * Actualiza los observadores de las entidades en la vista.
     */
    public void actualizarObserver();

    /**
     * Actualiza el tiempo del juego.
     * @param tiempo El nuevo tiempo del juego.
     */
    public void actualizarTiempoJuego(int tiempo);
	
    /**
     * Obtiene el objeto Juego que representa la lógica del juego.
     * @return El objeto Juego actual.
     */
    public Juego getJuego();
    


    /**
     * Muestra la Pantalla Modo de Juego desde la vista de juego.
     */
    public void mostrarPantallaModoJuego();

	public void setNombreUsuario(String nombreUsuario);
    
}
