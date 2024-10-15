package Entidades.Proyectiles;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class ProyectilSpiny extends Proyectil {

    public ProyectilSpiny(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
