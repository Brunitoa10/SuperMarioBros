package Entidades.Plataformas;

import Fabricas.Sprite;

public class BloqueSolido extends Plataforma {
    protected boolean meRompi=false;

    public BloqueSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

}
