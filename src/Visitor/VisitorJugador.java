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
            System.out.println("estoy colisionando mi rey");
            mario.setEnPlataforma(true);
            mario.setEstadoMovimiento(new MarioParado(mario));
            mario.setPosicionEnY((int) (plataforma.getHitbox().getMinY() - mario.getHitbox().getHeight()));
            mario.setPiso((int) plataforma.getHitbox().getMinY()-plataforma.getSprite().getAlto());

        }
        else if (mario.colisionArriba(plataforma)) {
                System.out.println("le pegue");
            if(!mario.getEstadoJugador().puedeRomperBloques()) {
                mario.setEstadoMovimiento(new MarioEnAire(mario));
            } else {
                System.out.println("lo romp");
                plataforma.getSprite().setRutaImagen(null);
                plataforma.setPosicionEnY(1000);
            }

        }
    }

    @Override
    public void visit(Proyectil proyectil) {

    }

    // ... (resto de tu código)

public void visit(Vacio vacio) {
        // Aumentar la tolerancia en la colisión abajo
        int tolerancia = 5;
        if (mario.colisionAbajo(vacio)) {
            System.out.println("colision abajo!");}
        if (mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia)
            System.out.println("picho");
        if (mario.colisionAbajo(vacio) && mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia) {
            System.out.println("colision abajo!");

            mario.setPiso(420);
            mario.setEstadoMovimiento(new MarioEnAire(mario));

        }
    }
}




