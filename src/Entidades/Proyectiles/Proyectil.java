package Entidades.Proyectiles;

import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.VisitorProyectil;

import java.util.List;

public abstract class Proyectil extends EntidadMovil {

    protected List<Proyectil> listaProyectilNivel;
    protected VisitorProyectil visitorProyectil;

    public Proyectil(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite);
        this.listaProyectilNivel = listaProyectilNivel;
        visitorProyectil = new VisitorProyectil(this);
    }

    public VisitorProyectil getVisitor() {
        return visitorProyectil;
    }


    public void eliminarEntidad() {
        listaProyectilNivel.remove(this);
    }

    public void Interactuar(Jugador j) {

    }

    public void setGravedad() {

    }

    public void activarGravedad() {
    }

    public abstract boolean puedeRomperBloques();
}
