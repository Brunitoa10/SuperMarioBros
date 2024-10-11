package Entidades.Enemigos;

import Fabricas.Sprite;

public class BuzzyBeetle extends Enemigo {
    private int PuntajeDeath;


    public BuzzyBeetle(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }
}
