package Entidades.Proyectiles;

import Entidades.EntidadMovil;
import Entidades.Power_Ups.PowerUp;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

import java.util.List;

public abstract class Proyectil extends EntidadMovil {

    protected List<Proyectil> listaProyectilNivel;

    public Proyectil(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite);
        this.listaProyectilNivel = listaProyectilNivel;
    }

    public VisitorProyectil getVisitor() {
        return new VisitorProyectil(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void eliminarEntidad() {
        listaProyectilNivel.remove(this);
    }

}
