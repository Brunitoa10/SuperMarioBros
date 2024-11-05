package Vista.Controladores;


public interface ControladorVista {

    /**
     * Inicia el juego en el modo especificado.
     * @param modoJuego El modo de juego a iniciar (por ejemplo, fácil, difícil, etc.).
     */
   public void accionarInicioJuego(String modoJuego);

    /**
     * Cambia a la pantalla de selección del modo de juego.
     */
   public void accionarPantallaModoJuego();

    /**
     * Cambia a la pantalla de ranking.
     */
   public void accionarPantallaRanking();

    /**
     * Cambia el modo de juego actual.
     * @param modoJuego El nuevo modo de juego a establecer.
     */
   public void cambiarModoJuego(String modoJuego);

    /**
     * Muestra la pantalla inicial del juego.
     * @param modoJuego El modo de juego a utilizar en la pantalla inicial.
     */
   public void mostrarPantallaInicial(String modoJuego);

    /**
     * Muestra la pantalla de ranking.
     */
   public void mostrarPantallaRanking();
  
    /**
     * Obtiene el modo de juego actual.
     * @return El modo de juego actual.
     */
   public String obtenerModoJuego();

    /**
     * Vuelve al panel anterior en la pila de historial de pantallas.
     */
   public void volverAlPanelAnterior();

    /**
     * Agrega un jugador al ranking.
     * @param nombre El nombre del jugador a agregar.
     * @param puntaje El puntaje del jugador a agregar.
     */
 //  public void agregarJugadorAlRanking(String nombre, int puntaje);

    /**
     * Establece el nombre del usuario.
     * @param nombreUsuario El nombre de usuario a establecer.
     */
   public void setNombreUsuario(String nombreUsuario);

    /**
     * Muestra la pantalla para ingresar el nombre del usuario.
     */
   public void mostrarPantallaNombreUsuario();
}
