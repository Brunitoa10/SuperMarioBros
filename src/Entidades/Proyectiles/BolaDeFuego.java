package Entidades.Proyectiles;

import Entidades.Colisionable;
import Entidades.Entidad;
import Entidades.EntidadMovil;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BolaDeFuego extends Proyectil {

    public BolaDeFuego(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
