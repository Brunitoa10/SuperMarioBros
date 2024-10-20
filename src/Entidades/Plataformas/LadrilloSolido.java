package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPlataforma;

public class LadrilloSolido extends Plataforma {
    protected VisitorPlataforma visitor;
    public LadrilloSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorPlataforma(this);
    }

    public boolean detectarColision(Entidad c) {
        System.out.println("detectarColision");
        return false;

    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void Interactuar(Jugador j){
        this.getSprite().setPosicionY(-100);
        this.setPosicionEnY(-100);

    }

    public Visitor getVisitor() {
        return visitor;
    }
}
