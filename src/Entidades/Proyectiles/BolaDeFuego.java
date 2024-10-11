package Entidades.Proyectiles;

import Entidades.Colisionable;
import Entidades.EntidadMovil;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BolaDeFuego extends Proyectil {

    public BolaDeFuego(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
