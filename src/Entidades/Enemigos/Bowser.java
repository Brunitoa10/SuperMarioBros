package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Bowser extends Enemigo {

    public Bowser(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }

    @Override
    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectColission(this);
        return false;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
