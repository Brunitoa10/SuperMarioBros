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

public class BuzzyBeetle extends Enemigo {

    protected VisitorEnemigo visitor;

    public BuzzyBeetle(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
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

    public void accept(Visitor v) {
        v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if(mario.colisionAbajo(this)) {
            this.setAEliminar();
            this.setPosicionEnY(-100);
            toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
        }
        else if(mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            if(mario.getEstadoJugador().esInmortal()) {
                this.setAEliminar();
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
            }
            else {
                mario.getEstadoJugador().recibeDanio();
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_MUERTE_MARIO;
            }
        }
        return toReturn;
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
    }

}
