package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Tuberia extends Plataforma {

    public Tuberia(int x, int y, Sprite sprite) {
        super(x, y, sprite);
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

    public void Interactuar(Jugador j){

    }
}
