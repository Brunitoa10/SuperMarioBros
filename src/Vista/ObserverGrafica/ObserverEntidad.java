package Vista.ObserverGrafica;

import Entidades.EntidadLogica;
import Vista.Paneles.PanelPantallaNivel;

public class ObserverEntidad extends ObserverGrafica {

    /**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected PanelPantallaNivel panelPantallaNivel;
    protected EntidadLogica entidadObservada;

    public ObserverEntidad(EntidadLogica entidadObservada,PanelPantallaNivel panelPantallaNivel) {
        super(entidadObservada);
        this.panelPantallaNivel = panelPantallaNivel;
        this.entidadObservada = entidadObservada;
        actualizarObserver();
    }

    public void actualizarObserver() {
        super.actualizarPosicionTamanio();
        super.actualizarObserver();
    }

    public void eliminarDePanel() {
        panelPantallaNivel.eliminarEntidad(this);
    }
}
