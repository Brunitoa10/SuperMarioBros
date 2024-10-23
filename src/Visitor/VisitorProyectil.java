package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

import java.sql.SQLOutput;

public class VisitorProyectil implements Visitor {
    Proyectil proyectil;

    public VisitorProyectil(Proyectil p){
        proyectil=p;
    }
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

    @Override
    public void visit(Plataforma p) {
            if(proyectil.colisionIzquierda(p) || proyectil.colisionDerecha(p))
                proyectil.setDireccion(-1*proyectil.getDireccion());
            if(proyectil.colisionAbajo(p))
                proyectil.setGravedad();
            if(proyectil.colisionArriba(p))
                proyectil.setGravedad();
    }

    @Override
    public void visit(Proyectil proyectil) {

    }
}
