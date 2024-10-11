package Entidades.Enemigos;

import Fabricas.Sprite;

public class Goomba extends Enemigo {
    private int PuntajeDeath;


    public Goomba(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }
}
