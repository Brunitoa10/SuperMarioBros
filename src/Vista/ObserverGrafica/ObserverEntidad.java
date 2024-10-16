package Vista.ObserverGrafica;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverEntidad extends ObserverGrafica {

    private PanelPantallaNivel panel_pantalla_nivel;
    private EntidadLogica entidadObservada;

    public ObserverEntidad(EntidadLogica entidadObservada,PanelPantallaNivel panel_pantalla_nivel) {
        super(entidadObservada);
        this.panel_pantalla_nivel = panel_pantalla_nivel;
        this.entidadObservada = entidadObservada;
        actualizarObserver();
    }

    public void actualizarObserver() {
        super.actualizarObserver();
    }

    public EntidadLogica getEntidadObservada() {
        return entidadObservada;
    }
}
