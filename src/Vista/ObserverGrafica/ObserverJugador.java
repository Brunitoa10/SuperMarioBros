package Vista.ObserverGrafica;

import Entidades.EntidadJugador;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverJugador extends ObserverGrafica {

    private PanelPantallaNivel panelPantallaNivel;
    private EntidadJugador jugadorObservado;

    public ObserverJugador(PanelPantallaNivel panelPantallaNivel, EntidadJugador jugadorObservado) {
        super(jugadorObservado);
        this.panelPantallaNivel = panelPantallaNivel;
        this.jugadorObservado = jugadorObservado;
        actualizarObserver();
    }

    @Override
    public void actualizarObserver() {
        super.actualizarObserver();
        panelPantallaNivel.actualizarScrollHaciaJugador(jugadorObservado);
    }

    public EntidadJugador getJugadorObservado() {
        return jugadorObservado;
    }

    public void eliminarDePanel() {
        panelPantallaNivel.getImagenFondoPanelNivel().remove(this);
    }
}
