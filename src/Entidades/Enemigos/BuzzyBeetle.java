package Entidades.Enemigos;

import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

import Constantes.ConstantesPuntaje;

public class BuzzyBeetle extends Enemigo {

    protected VisitorEnemigo visitor;

    public BuzzyBeetle(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 4;
    }

    public int accept(Visitor v) {
        return v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
            if (mario.colisionAbajo(this)) {
                this.setAEliminar();
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                    this.setAEliminar();
                    toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;

                    mario.getEstadoJugador().recibeDanio(this);
                    if (mario.getMorir())
                        toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_MUERTE_MARIO;
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