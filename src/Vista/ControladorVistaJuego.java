package Vista;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;

public interface ControladorVistaJuego {
    public Observer registrar_entidad(EntidadLogica entidad_logica);

    public Observer registrar_entidad(EntidadJugador entidad_jugador);

    public void mostrar_pantalla_nivel();

    public void mostrar_pantalla_fin_juego();

    public void mostar_pantalla_ranking();
}