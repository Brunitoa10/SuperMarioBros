package Vista;

import Entidades.EntidadJugador;

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
    public void actualizar_imagen() {

    }

    @Override
    public void actualizar_posicion_tamanio() {

    }

    @Override
    public void actualizar_observer() {
        super.actualizar_observer();
    }
}
