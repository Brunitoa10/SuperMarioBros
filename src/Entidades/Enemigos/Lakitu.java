package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.Visitor;

import java.util.List;

public class Lakitu extends Enemigo {

    public Lakitu(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        velocidad = 4;
    }


    public int accept(Visitor v) {
        return v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mario.getEstadoJugador().esInmortal() || mario.getEstadoJugador().estadoEstrella()) {
            if (mario.colisionAbajo(this)) {

                this.setAEliminar();
                this.setPosicionEnY(-100);
                toReturn = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                if (mario.getEstadoJugador().esInmortal()) {
                    this.setAEliminar();
                    toReturn = (ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO);
                }
            }
        }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeLakituDestruido = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeLakituDestruido;
    }

}
