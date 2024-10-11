package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;

    public class VisitorJugador implements Visitor {

    @Override
    public void visit(Jugador J) {

    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public void visit(PowerUp p) {

    }

    @Override
    public void visit(Moneda m) {

    }
}
