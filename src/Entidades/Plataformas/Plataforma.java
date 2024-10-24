package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.EntidadInmovil.EntidadInmovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;
    protected boolean meRompi=false;
    protected List<Plataforma> listaPlataformaNivel;

    public Plataforma(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite);
        this.listaPlataformaNivel = listaPlataformaNivel;
    }

    public boolean detectarColision(Entidad c) {
        return this.getHitbox().intersects(c.getHitbox());
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void eliminarEntidad() {
        listaPlataformaNivel.remove(this);
    }

    public void interactuar(Jugador j){

    }
}
