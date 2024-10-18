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

    public void visit(Plataforma plataforma){
        if (mario.colisionDerecha(plataforma)){
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMinX() - mario.getHitbox().getWidth());
            mario.setPosicionEnX(posicionATeletransportar);
        }
        else if (mario.colisionIzquierda(plataforma)){
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMaxX());
            mario.setPosicionEnX(posicionATeletransportar);
        }
        else if (mario.colisionAbajo(plataforma)){
            mario.setEstadoMovimiento(new MarioParado(mario));
            mario.setPosicionEnY((int) (plataforma.getHitbox().getMinY() - mario.getHitbox().getHeight()));
            mario.setPiso((int) plataforma.getHitbox().getMinY());
            mario.setEnPlataforma(true);
        }
        else if (mario.colisionArriba(plataforma)) {
            System.out.println("colision arriba!");
        }
    }

    @Override
    public void visit(Proyectil proyectil) {

    }

    public void visit(Vacio vacio) {
        if (mario.colisionAbajo(vacio)) {
            mario.setEstadoMovimiento(new MarioEnAire(mario, 1));
        }
    }
}




