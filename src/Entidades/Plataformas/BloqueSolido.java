package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPlataforma;

public class BloqueSolido extends Plataforma {
    protected VisitorPlataforma visitor;
    protected boolean meRompi=false;

    public BloqueSolido(int x, int y, Sprite sprite) {
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

    public boolean Roto(){
        return meRompi;
    }
}
