package Entidades.Plataformas;

import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;


    public Plataforma(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
}
