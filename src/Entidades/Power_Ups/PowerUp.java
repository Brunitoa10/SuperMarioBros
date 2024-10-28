package Entidades.Power_Ups;

import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public abstract class PowerUp extends EntidadMovil {
    protected boolean FrenarJuego = false;
    protected List<PowerUp> listaPowerUpsNivel;

    public PowerUp(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite);
        this.listaPowerUpsNivel = listaPowerUpsNivel;
    }

    public int accept(Visitor v) {
       return v.visit(this);
    }


    public abstract int setEstadoMario(Jugador j);

    public void eliminarEntidad() {
        listaPowerUpsNivel.remove(this);
    }

    public boolean getFrenarJuego() {
        return FrenarJuego;
    }

    public void setFrenarJuego(boolean frenarJuego) {
        FrenarJuego = frenarJuego;
    }

}
