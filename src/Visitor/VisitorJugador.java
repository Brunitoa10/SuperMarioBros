package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;

public class VisitorJugador implements Visitor {

    protected Jugador mario;

    public VisitorJugador(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public int visit(Jugador J) {
        return 0;
    }

    @Override
    public int visit(Enemigo e) {
        return 0;
    }

    @Override
    public int visit(PowerUp p) {
        int toReturn = p.setEstadoMario(mario);
        p.setAEliminar();
        return toReturn;
    }

    @Override
    public void visit(Moneda moneda) {
        moneda.setAEliminar();
    }

    public void visit(Plataforma plataforma) {
        if (mario.colisionDerecha(plataforma)) {
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMinX() - mario.getHitbox().getWidth());
            mario.setPosicionEnX(posicionATeletransportar);
        } else if (mario.colisionIzquierda(plataforma)) {
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMaxX());
            mario.setPosicionEnX(posicionATeletransportar);
        } else if (mario.colisionAbajo(plataforma)) {
            mario.setEnPlataforma(true);
            mario.setEstadoMovimiento(new MarioParado(mario));
            mario.setPosicionEnY((int) (plataforma.getHitbox().getMinY() - mario.getHitbox().getHeight()));

        } else if (mario.colisionArriba(plataforma)) {
            mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
            plataforma.interactuar(mario);
            System.out.println("colision arriba!");
        }
    }

    @Override
    public int visit(Proyectil proyectil) {
        proyectil.Interactuar(mario);
        return 0;
    }


    public void visit(Vacio vacio) {
        int tolerancia = 5;
        if (mario.colisionAbajo(vacio) && mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia) {
            mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
        }
    }
}




