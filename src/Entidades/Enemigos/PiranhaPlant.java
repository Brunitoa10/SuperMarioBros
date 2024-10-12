package Entidades.Enemigos;

import Entidades.Colisionable;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class PiranhaPlant extends Enemigo {
    private int PuntajeDeath;


    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 0;
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
