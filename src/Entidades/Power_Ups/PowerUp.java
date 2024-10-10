package Entidades.Power_Ups;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class PowerUp extends EntidadMovil {

    protected int puntaje;

    public PowerUp(int x, int y, Sprite sprite, int puntaje) {
        super(x, y, sprite);
        this.puntaje = puntaje;
    }

}
