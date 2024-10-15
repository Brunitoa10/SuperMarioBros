package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Enemigo extends EntidadMovil {

    protected int puntajeKill;
    protected int posicionx;
    protected int posiciony;

    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
        caminar();
    }

    public void caminar() {
        posicionx += sprite.getPosicionX() + 3;
    }

}