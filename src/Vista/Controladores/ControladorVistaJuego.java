package Vista.Controladores;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.OyenteTeclado;
import Vista.ObserverGrafica.Observer;

public interface ControladorVistaJuego {
    public Observer registrarEntidad(EntidadLogica entidadLogica);

    public Observer registrarEntidad(EntidadJugador entidadJugador);

    public void mostrarPantallaNivel();

    public void mostrarPantallaFinJuego();

    public void mostrarPantallaRanking();

    public void refrescar();

    public void actualizarObserver();

    public OyenteTeclado oyenteTeclado();
}