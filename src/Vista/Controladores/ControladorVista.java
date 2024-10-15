package Vista.Controladores;

public interface ControladorVista {
    public void accionarInicioJuego();

    public void accionarPantallaModoJuego();

    public void accionarPantallaRanking();

    public void cambiarModoJuego(String modo_juego);

    public void mostrarPantallaInicial();

    public void mostrarPantallaRanking();

    public void refrescar();
}
