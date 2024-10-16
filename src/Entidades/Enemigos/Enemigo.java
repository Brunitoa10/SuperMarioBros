package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Enemigo extends EntidadMovil {

    private int puntajeDestruido;

    protected int puntajeKill;
    protected int posicionx;
    protected int posiciony;


    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
    }

    public void caminar() {
        posicionx += sprite.getPosicionX() + 3;
    }

    public void actualizar() {
        // Mover al enemigo horizontalmente
        posicionx = posicionx + 1;
        setPosicionEnX(posicionx);
    }

}