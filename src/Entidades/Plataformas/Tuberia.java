package Entidades.Plataformas;

import Entidades.Colisionable;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Tuberia extends Plataforma {

    public Tuberia(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
