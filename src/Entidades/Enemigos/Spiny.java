package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

public class Spiny extends Enemigo {

    protected VisitorEnemigo visitor;

    public Spiny(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
    }


    public int accept(Visitor v) {
        return v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mario.getEstadoJugador().esInmortal() || mario.getEstadoJugador().estadoEstrella()) {
            if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                if (mario.getEstadoJugador().esInmortal()) {
                    this.setAEliminar();
                    toReturn = ConstantesPuntaje.PUNTAJE_SPINY_DESTRUIDO;
                } else {
                    mario.getEstadoJugador().recibeDanio();
                    if (mario.getMorir())
                        toReturn = ConstantesPuntaje.PUNTAJE_SPINY_MUERTE_MARIO;
                }

            }
        }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeSpinyDestruido = ConstantesPuntaje.PUNTAJE_SPINY_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeSpinyDestruido;
    }

}
