package Entidades.Plataformas;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class SueloNivel extends Plataforma {
    public SueloNivel(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad entidad) {
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
