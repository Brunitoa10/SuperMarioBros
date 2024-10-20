package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

public class VisitorPlataforma implements Visitor {

    protected Plataforma plataforma;

    public VisitorPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }


    @Override
    public void visit(Jugador j) {
        plataforma.interactuar(j);
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

    @Override
    public void visit(Plataforma p) {

    }

    @Override
    public void visit(Proyectil proyectil) {

    }
}
