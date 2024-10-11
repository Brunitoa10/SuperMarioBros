package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;

public class ConcreteVisitor implements Visitor {
    @Override
    public void visitPersonaje(Jugador j) {
        // Implementación del comportamiento para Jugador
    }

    @Override
    public void visitEnemigo(Enemigo e) {
        // Implementación del comportamiento para Enemigo
    }

    @Override
    public void visitPowerUp(PowerUp p) {
        // Implementación del comportamiento para PowerUp
    }

    @Override
    public void visitMoneda(Moneda m) {
        // Implementación del comportamiento para Moneda
    }
}
