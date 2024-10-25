package Entidades.EntidadInmovil;

import Entidades.Entidad;
import Fabricas.Sprite;

public abstract class EntidadInmovil extends Entidad {

    protected final int velocidad = 0;
    protected final int direccion = 0;


    public EntidadInmovil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

}

