package Entidades.Enemigos;

import Fabricas.Sprite;

public class Bowser extends Enemigo {
    private int puntajeDeath;


    public Bowser(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 4;
    }

}
