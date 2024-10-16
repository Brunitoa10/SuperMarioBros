package Vista.ObserverGrafica;

import Entidades.EntidadLogica;

public class ObserverEntidad extends ObserverGrafica {

    public ObserverEntidad(EntidadLogica entidadObservada) {
        super(entidadObservada);
        actualizarObserver();
    }
}
