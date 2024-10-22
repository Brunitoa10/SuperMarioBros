package Entidades;

import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Vacio extends EntidadInmovil {
    public Vacio(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean detectarColision(Entidad c) {
        return false;
    }

    @Override
    public int accept(Visitor v) {

        return 0;
    }
}
