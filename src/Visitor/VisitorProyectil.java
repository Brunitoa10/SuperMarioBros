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
    public int visit(Jugador j) {
        return 0;
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
        System.out.println("Colisione anashe");
        if(proyectil.colisionIzquierda(p) || proyectil.colisionDerecha(p)){
           proyectil.setDireccion(-1*proyectil.getDireccion());
        }
    }

    @Override
    public void visit(Proyectil proyectil) {

    }
}
