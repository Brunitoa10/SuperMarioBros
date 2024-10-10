package Entidades.Enemigos;

import Fabricas.Sprite;

public class Lakitu extends Enemigo {
    private int PuntajeDeath;


    public Lakitu(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }
}
