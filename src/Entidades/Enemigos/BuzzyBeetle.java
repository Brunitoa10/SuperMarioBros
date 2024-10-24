package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import EstadoJugador.MarioEstrella;
import EstadoMovimiento.MarioEnAire;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

public class BuzzyBeetle extends Enemigo {

    protected VisitorEnemigo visitor;

    public BuzzyBeetle(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite,new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 4;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        if (colisionan) {
            // Si hay colisión con el jugador, cambia el comportamiento a atacar
            setComportamientoIA(new IAAtacar());
        }
        return colisionan;
    }

    public void interactuar(Jugador mario) {
        if(mario.colisionAbajo(this)) {
            this.setAEliminar();
            this.setPosicionEnY(-100);
            mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO);
        }
        else if(mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            if(mario.getEstadoJugador().esInmortal()) {
                this.setAEliminar();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO);
            }
            else {
                mario.getEstadoJugador().recibeDanio();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_MUERTE_MARIO);
            }
        }
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
        proyectil.setDireccion(0);
    }

}
