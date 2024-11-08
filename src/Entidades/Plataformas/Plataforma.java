package Entidades.Plataformas;

import Entidades.EntidadInmovil.EntidadInmovil;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public abstract class Plataforma extends EntidadInmovil {

    protected List<Plataforma> listaPlataformaNivel;

    public Plataforma(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite);
        this.listaPlataformaNivel = listaPlataformaNivel;
    }

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public void eliminarEntidad() {
        listaPlataformaNivel.remove(this);
    }

    public void interactuar(Jugador j) {

    }

    public void reaccionar(Proyectil p) {

    }

}
