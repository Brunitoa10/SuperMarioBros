package Entidades.Enemigos;

import Entidades.Colisionable;
import Entidades.EntidadMovil;
import Fabricas.Sprite;
import Visitor.Visitor;

public abstract class Enemigo extends EntidadMovil {

    protected int puntajeKill;


    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

}