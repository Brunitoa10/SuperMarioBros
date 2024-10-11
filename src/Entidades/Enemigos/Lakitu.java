package Entidades.Enemigos;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Lakitu extends Enemigo {
    private int PuntajeDeath;


    public Lakitu(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
