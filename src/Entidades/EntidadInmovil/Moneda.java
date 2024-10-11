package Entidades.EntidadInmovil;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Moneda extends EntidadInmovil{

    private int puntaje;


    public Moneda(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
