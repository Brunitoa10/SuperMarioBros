package Entidades.EntidadInmovil;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Moneda extends EntidadInmovil{

    public Moneda(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


    @Override
    public boolean detectarColision(Entidad c) {

        return false;
    }

    @Override
    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }
}
