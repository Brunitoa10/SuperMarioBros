package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Goomba extends Enemigo {

    public Goomba(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan = c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
