package Entidades.Enemigos;

import Fabricas.Sprite;

public class PiranhaPlant extends Enemigo {
    private int PuntajeDeath;


    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 0;
    }
}
