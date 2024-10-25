package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

import Constantes.ConstantesPuntaje;

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
      public int accept(Visitor v) {
        return v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mario.getEstadoJugador().esInmortal() || mario.getEstadoJugador().estadoEstrella()) {
            if (mario.colisionAbajo(this)) {
                this.setAEliminar();
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                if (mario.getEstadoJugador().esInmortal()) {
                    this.setAEliminar();
                    toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
                } else {
                    mario.getEstadoJugador().recibeDanio();
                    if (mario.getMorir())
                    toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_MUERTE_MARIO;
                }
            }
        }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeBuzzyBeetleDestruido = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeBuzzyBeetleDestruido;
    }
}