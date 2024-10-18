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
            mario.setPiso((int) plataforma.getHitbox().getMinY()-plataforma.getSprite().getAlto());
            mario.setEnPlataforma(true);
        }
        else if (mario.colisionArriba(plataforma)) {
            System.out.println("colision arriba!");
        }
    }

    @Override
    public void visit(Proyectil proyectil) {

    }

    // ... (resto de tu código)

public void visit(Vacio vacio) {
    // Aumentar la tolerancia en la colisión abajo
    int tolerancia = 32;
    if (mario.colisionAbajo(vacio) && mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia) {
        mario.setEstadoMovimiento(new MarioEnAire(mario, 1));
    }
}
}




