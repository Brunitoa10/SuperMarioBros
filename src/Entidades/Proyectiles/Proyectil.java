package Entidades.Proyectiles;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Proyectil extends EntidadMovil {

    public Proyectil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

}
