package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPlataforma;
public class SueloNivel extends Plataforma {
    public SueloNivel(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorPlataforma(this);
    }

    public boolean detectarColision(Entidad entidad) {
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void interactuar(Jugador j){

    }
}
