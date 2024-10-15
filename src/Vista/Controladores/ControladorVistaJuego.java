package Vista.Controladores;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.OyenteTeclado;
import Vista.ObserverGrafica.Observer;

public interface ControladorVistaJuego {
    public Observer registrarEntidad(EntidadLogica entidad_logica);

    public Observer registrarEntidad(EntidadJugador entidad_jugador);

    public void mostrarPantallaNivel();

    public void mostrarPantallaFinJuego();

    public void mostrarPantallaRanking();

    public void refrescar();

    public void actualizarObserver();

    OyenteTeclado oyenteTeclado();
}