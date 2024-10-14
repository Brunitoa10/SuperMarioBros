package Vista.Controladores;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.ObserverGrafica.Observer;

public interface ControladorVistaJuego {
    public Observer registrar_entidad(EntidadLogica entidad_logica);

    public Observer registrar_entidad(EntidadJugador entidad_jugador);

    public void mostrar_pantalla_nivel();

    public void mostrar_pantalla_fin_juego();

    public void mostrar_pantalla_ranking();

    public void refrescar();
}