package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.EntidadInmovil.EntidadInmovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;
    protected boolean meRompi=false;

    public Plataforma(int x, int y, Sprite sprite) {

        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {
        return this.getHitbox().intersects(c.getHitbox());
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void interactuar(Jugador j){

    }
}
