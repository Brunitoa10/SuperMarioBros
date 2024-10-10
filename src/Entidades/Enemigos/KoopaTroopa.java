package Entidades.Enemigos;

import Fabricas.Sprite;

public class KoopaTroopa extends Enemigo{
    private int PuntajeDeath;


    public KoopaTroopa(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }
}
