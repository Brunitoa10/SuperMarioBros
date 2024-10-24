package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public class Tuberia extends Plataforma {

    protected boolean meRompi=false;

    public Tuberia(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
    }


}
