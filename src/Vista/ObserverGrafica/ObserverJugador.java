package Vista.ObserverGrafica;

import Entidades.EntidadJugador;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverJugador extends ObserverGrafica {

    private PanelPantallaNivel panel_pantalla_nivel;
    private EntidadJugador jugador_observado;

    public ObserverJugador(PanelPantallaNivel panel_pantalla_nivel, EntidadJugador jugador_observado) {
        super(jugador_observado);
        this.panel_pantalla_nivel = panel_pantalla_nivel;
        this.jugador_observado = jugador_observado;
        actualizar_observer();
    }

    @Override
    public void actualizar_observer() {
        super.actualizar_observer();
        // panel_pantalla_nivel.actualizar_estado_jugador(jugador_observado);
    }
}
