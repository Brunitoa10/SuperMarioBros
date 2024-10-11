package Entidades.Proyectiles;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Visitor.Visitor;

public class ProyectilSpiny extends Proyectil {

    public ProyectilSpiny(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
