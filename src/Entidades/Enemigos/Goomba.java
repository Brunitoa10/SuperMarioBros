package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.VisitorEnemigo;
import Visitor.Visitor;

public class Goomba extends Enemigo {

    protected VisitorEnemigo visitor;

    public Goomba(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void interactuar(Jugador mario) {
        if(mario.colisionAbajo(this)) {
            this.setAEliminar();
            this.setPosicionEnY(-100);
            mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO);
        }
        else if(mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            if(mario.getEstadoJugador().esInmortal()) {
                this.setAEliminar();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO);
            }
            else {
                mario.getEstadoJugador().recibeDanio();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_MUERTE_MARIO);
                mario.setVidas(mario.getVidas() - 1);

            }
        }
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
    }

}
