package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.EntidadMovil;
import Fabricas.Sprite;
import Visitor.Visitor;

public abstract class PowerUp extends EntidadMovil {

    protected int puntaje;

    public PowerUp(int x, int y, Sprite sprite) {
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
