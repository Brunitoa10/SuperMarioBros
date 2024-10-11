package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;

public interface Visitor {
    public void visitPersonaje(Jugador j);
    public void visitEnemigo(Enemigo e);
    public void visitPowerUp(PowerUp p);
    public void visitMoneda(Moneda m);
}
