package Entidades.Plataformas;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class LadrilloSolido extends Plataforma {

    public LadrilloSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {
        System.out.println("detectarColision");
        return c.detectColission(this);

    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
