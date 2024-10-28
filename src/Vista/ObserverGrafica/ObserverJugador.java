package Vista.ObserverGrafica;

import Entidades.EntidadJugador;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverJugador extends ObserverGrafica {

    /**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected PanelPantallaNivel panelPantallaNivel;
    protected EntidadJugador jugadorObservado;

    public ObserverJugador(PanelPantallaNivel panelPantallaNivel, EntidadJugador jugadorObservado) {
        super(jugadorObservado);
        this.panelPantallaNivel = panelPantallaNivel;
        this.jugadorObservado = jugadorObservado;
        actualizarObserver();
    }

    @Override
    public void actualizarObserver() {
        super.actualizarObserver();
        panelPantallaNivel.ajustarScrollHaciaJugador(jugadorObservado);
    }

    public EntidadJugador getJugadorObservado() {
        return jugadorObservado;
    }

    public void eliminarDePanel() {
        panelPantallaNivel.eliminarEntidadJugador(this);
    }
}
