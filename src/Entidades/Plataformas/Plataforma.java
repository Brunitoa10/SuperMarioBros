package Entidades.Plataformas;

import Entidades.Colisionable;
import Entidades.Entidad;
import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPlataforma;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;
    protected VisitorPlataforma VPlataforma;


    public Plataforma(int x, int y, Sprite sprite) {

        super(x, y, sprite);
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
