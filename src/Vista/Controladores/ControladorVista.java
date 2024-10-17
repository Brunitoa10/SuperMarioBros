package Vista.Controladores;

public interface ControladorVista {
    public void accionarInicioJuego(String modoJuego);

    public void accionarPantallaModoJuego();

    public void accionarPantallaRanking();

    public void cambiarModoJuego(String modoJuego);

    public void mostrarPantallaInicial(String modoJuego);

    public void mostrarPantallaRanking();

    public void refrescar();

	public String obtenerModoJuego();

}
