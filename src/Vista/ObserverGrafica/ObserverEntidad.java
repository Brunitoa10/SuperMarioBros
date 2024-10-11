package Vista.ObserverGrafica;

import Entidades.EntidadLogica;

public class ObserverEntidad extends ObserverGrafica {

    public ObserverEntidad(EntidadLogica entidad_observada) {
        super(entidad_observada);
        actualizar_observer();
    }
}
