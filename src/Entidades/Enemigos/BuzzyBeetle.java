package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BuzzyBeetle extends Enemigo {

    private int puntajeMuerteMario;

    public BuzzyBeetle(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
