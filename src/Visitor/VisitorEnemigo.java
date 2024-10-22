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

    public void visit(Jugador j) {
        enemigo.interactuar(j);
    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public int visit(Moneda m) {
        return 0;
        //m.eliminar();
    }

    @Override
    public int visit(PowerUp m) {
        return 0;
    }

    public void visit(Plataforma p) {
        if (enemigo.colisionDerecha(p)) {
            enemigo.setDireccion(enemigo.getDireccion()*(-1));
        }
    }

    public void visit(Proyectil proyectil){
        enemigo.interactuarConProyectil(proyectil);
        proyectil.setAEliminar();
        proyectil.setPosicionEnY(-100);
    }
}
