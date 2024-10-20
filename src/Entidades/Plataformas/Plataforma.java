package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.EntidadInmovil.EntidadInmovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPlataforma;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;
    protected VisitorPlataforma visitor;


    public Plataforma(int x, int y, Sprite sprite) {

        super(x, y, sprite);
        visitor = new VisitorPlataforma(this);
    }

    public boolean detectarColision(Entidad c) {
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Visitor getVisitor() {
        return visitor;
    }
    public void interactuar(Jugador j){

    }
}
