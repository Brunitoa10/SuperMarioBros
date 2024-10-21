package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Tuberia extends Plataforma {

    protected boolean meRompi=false;

    public Tuberia(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


}
