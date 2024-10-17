package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import EstadoMovimiento.MarioParado;

public class VisitorJugador implements Visitor {

    protected Jugador mario;

    public VisitorJugador(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void visit(Jugador J) {

    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public void visit(PowerUp p) {

    }

    @Override
    public void visit(Moneda m) {

    }

    public void visit(Plataforma p){
        if (mario.colisionArriba(p)) {
            mario.setPiso((int)p.getHitbox().getMinY() - mario.getHitbox().height);
            System.out.println("colision arriba");
        }
        else if (mario.colisionIzquierda(p)) {
            mario.setPosicionEnX((int) p.getHitbox().getMinX() - mario.getHitbox().width);
            System.out.println((int) p.getHitbox().getMinX() - mario.getHitbox().width);
            System.out.println(mario.getPosicionEnX());
        }
    }

    @Override
    public void visit(Proyectil proyectil) {

    }
}




