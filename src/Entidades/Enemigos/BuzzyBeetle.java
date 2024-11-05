package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;

import java.util.List;

public class BuzzyBeetle extends Enemigo {


    public BuzzyBeetle(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        velocidad = 1;
    }

    public void actualizar() {
        super.actualizar();
        if (this.getDireccion() == 1) {
            this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/BuzzyBeetle/BuzzyBeetleRigthLoop.gif");
        }
        if (this.getDireccion() == -1) {
            this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/BuzzyBeetle/BuzzyBeetleLeftLoop.gif");
        }
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (mario.colisionAbajo(this)) {
            this.setAEliminar();
            toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
        } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            mario.getEstadoJugador().recibeDanio(this);
            if (this.aEliminar())
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
            else if (mario.getMorir())
                toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_MUERTE_MARIO;
        }


        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int toReturn = 0;
        if (proyectil.getDireccion()!=0) {
            toReturn = ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO;
        }
        return super.interactuarConProyectil(proyectil) + toReturn;
    }


}