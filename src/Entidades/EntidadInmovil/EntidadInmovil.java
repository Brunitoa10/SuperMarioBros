package Entidades.EntidadInmovil;

import Entidades.Colisionable;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public abstract class EntidadInmovil extends Entidad {

    protected final int velocidad = 0;
    protected final int direccion = 0;


    public EntidadInmovil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
}

