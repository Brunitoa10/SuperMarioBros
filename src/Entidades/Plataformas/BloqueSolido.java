package Entidades.Plataformas;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BloqueSolido extends Plataforma {

    public BloqueSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {

        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
