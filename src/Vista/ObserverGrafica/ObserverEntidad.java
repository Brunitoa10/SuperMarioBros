package Vista.ObserverGrafica;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverEntidad extends ObserverGrafica {

    private PanelPantallaNivel panelPantallaNivel;
    private EntidadLogica entidadObservada;

    public ObserverEntidad(EntidadLogica entidadObservada,PanelPantallaNivel panelPantallaNivel) {
        super(entidadObservada);
        this.panelPantallaNivel = panelPantallaNivel;
        this.entidadObservada = entidadObservada;
        actualizarObserver();
    }

    public void actualizarObserver() {
        super.actualizarObserver();
    }

    public void eliminarDePanel() {
        panelPantallaNivel.getImagenFondoPanelNivel().remove(this);
    }
}
