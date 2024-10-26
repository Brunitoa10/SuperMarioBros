package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

public class VisitorEnemigo implements Visitor {

    protected Enemigo enemigo;

    public VisitorEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public int visit(Jugador j) {
        return enemigo.interactuar(j);
    }

    @Override
    public int visit(Enemigo e) {

        return 0;
    }

    @Override
    public void visit(Moneda m) {
    }

    @Override
    public int visit(PowerUp m) {
        return 0;
    }

    public void visit(Plataforma plataforma) {
        if (enemigo.colisionIzquierda(plataforma) || enemigo.colisionDerecha(plataforma)) {
            enemigo.setDireccion(enemigo.getDireccion() * (-1));
        }
        if(enemigo.colisionAbajo(plataforma)){
            enemigo.setEnPlataforma(true);
        }
    }

    public int visit(Proyectil proyectil){
        return enemigo.interactuarConProyectil(proyectil);

    }

}
