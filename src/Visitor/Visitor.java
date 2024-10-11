package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;

public interface Visitor {
    public void visit(Jugador j);
    public void visit(Enemigo e);
    public void visit(Moneda p);
    public void visit(PowerUp m);
}
