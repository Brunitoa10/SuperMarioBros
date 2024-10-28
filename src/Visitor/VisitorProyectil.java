package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;

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
    public int visit(PowerUp m) {
        return 0;
    }

    @Override
    public void visit(Plataforma p) {
            if(proyectil.colisionIzquierda(p) || proyectil.colisionDerecha(p))
                proyectil.setDireccion(-1 * proyectil.getDireccion());
            if(proyectil.colisionAbajo(p))
                proyectil.setGravedad();
            if(proyectil.colisionArriba(p))
                proyectil.setGravedad();
            p.reaccionar(proyectil);
    }

    @Override
    public int visit(Proyectil proyectil) {

        return 0;
    }

    public void visit(Vacio vacio){
        proyectil.activarGravedad();
    }
}
