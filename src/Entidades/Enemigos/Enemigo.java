package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Enemigo extends EntidadMovil {

    protected int puntajeKill;


    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
}