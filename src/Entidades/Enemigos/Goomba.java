package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Logica.Temporizador;

import java.util.List;

public class Goomba extends Enemigo {

    protected boolean mori;
    protected Temporizador temporizadorGoomba;

    public Goomba(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        velocidad = 1;
        temporizadorGoomba = new Temporizador();
    }

    @Override
    public void actualizar() {
        if (temporizadorGoomba.hanPasadoNSegundos(500)) {
            this.setAEliminar();
        }
        super.actualizar();
    }


    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mori)
            if (mario.colisionAbajo(this)) {
                temporizadorGoomba.iniciar();
                this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/Goomba/GoombaMuerto.png");
                this.setPosicionEnY(this.getPosicionEnY() + 14);
                this.setDireccion(0);
                mori = true;
                toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                mario.getEstadoJugador().recibeDanio(this);
                if (this.aEliminar()) {
                    mori = true;
                    toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
                } else if (mario.getMorir()) {
                    toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_MUERTE_MARIO;
                }
            }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int toReturn = 0;
        if (proyectil.getDireccion()!=0) {
            toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
        }
        return super.interactuarConProyectil(proyectil) + toReturn;
    }

}