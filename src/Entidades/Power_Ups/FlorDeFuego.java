package Entidades.Power_Ups;

import Entidades.Colisionable;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class FlorDeFuego extends PowerUp {

    public FlorDeFuego(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
