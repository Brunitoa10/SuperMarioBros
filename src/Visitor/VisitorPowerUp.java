package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

public class VisitorPowerUp implements Visitor {

    protected PowerUp powerUp;

    public VisitorPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void visit(Jugador j) {

    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public void visit(Moneda p) {

    }

    @Override
    public void visit(PowerUp m) {

    }

    public void visit(Plataforma p){

    }

    public void visit(Proyectil proyectil){

    }
}
