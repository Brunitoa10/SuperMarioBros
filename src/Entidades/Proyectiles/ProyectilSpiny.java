package Entidades.Proyectiles;

import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public class ProyectilSpiny extends Proyectil {

    public ProyectilSpiny(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite, listaProyectilNivel);
    }

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public boolean puedeRomperBloques() {
        return false;
    }
}
