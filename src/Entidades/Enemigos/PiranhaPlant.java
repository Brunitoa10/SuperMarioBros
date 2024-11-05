package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IAPiranhaPlant;
import Visitor.VisitorEnemigo;

import java.util.List;

public class PiranhaPlant extends Enemigo {

    protected int yEscondido;


    public PiranhaPlant(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IAPiranhaPlant(y, y - sprite.getAlto()), listaEnemigoNivel);
        velocidad = 1;
        yEscondido = y;
    }

    public int interactuar(Jugador mario) {
        int toRet = 0;
        if ((mario.colisionAbajo(this) || mario.colisionDerecha(this)
                || mario.colisionIzquierda(this)) && !estaEscondido()) {
            mario.getEstadoJugador().recibeDanio(this);
            if (mario.getMorir()) {
                toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_MUERTE_MARIO;
            } else if (this.aEliminar()) {
                toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
            }
        }
        return toRet;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int toRet = 0;
        if (!estaEscondido() && proyectil.getDireccion() != 0) {
            toRet = super.interactuarConProyectil(proyectil) + ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
        }
        return toRet;
    }


    public boolean estaEscondido() {
        return yEscondido == posicionY;
    }
}
