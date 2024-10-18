package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.Visitor;

public class Goomba extends Enemigo {

    private int puntajeMuerteMario;

    public Goomba(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        velocidad = 4;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
