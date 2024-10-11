package Entidades.Enemigos;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Visitor.Visitor;

public class PiranhaPlant extends Enemigo {
    private int PuntajeDeath;


    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 0;
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
