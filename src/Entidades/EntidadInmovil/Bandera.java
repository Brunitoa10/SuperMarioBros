package Entidades.EntidadInmovil;

import Fabricas.Sprite;
import Visitor.Visitor;

public class Bandera extends EntidadInmovil {
    public Bandera(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public int accept(Visitor v) {

        return 0;
    }

    @Override
    public void eliminarEntidad() {

    }
}
