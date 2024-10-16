package Vista.Controladores;

public interface ControladorVista {
    public void accionarInicioJuego();

    public void accionarPantallaModoJuego();

    public void accionarPantallaRanking();

    public void cambiarModoJuego(String modoJuego);

    public void mostrarPantallaInicial();

    public void mostrarPantallaRanking();

    public void refrescar();
}
