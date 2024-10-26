package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

public class PiranhaPlant extends Enemigo {

    protected VisitorEnemigo visitor;

    public PiranhaPlant(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 0;
    }


    public int accept(Visitor v) {
        return v.visit(this);
    }

    public int interactuar(Jugador mario) {
        int toRet = 0;
        if (mario.colisionAbajo(this) || mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            if (!mario.getEstadoJugador().esInmortal()) {
                mario.getEstadoJugador().recibeDanio(this);
                if (mario.getMorir()) {
                    toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_MUERTE_MARIO;
                    System.out.println("mario pierde vida");
                }
            } else if (mario.getEstadoJugador().estadoEstrella()) {
                this.setAEliminar();
                toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
                System.out.println("mario mata piranha");
            }
        }
        return toRet;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajePiranhaPlantDestruida = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajePiranhaPlantDestruida;
    }

}
