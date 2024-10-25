package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public abstract class PowerUp extends EntidadMovil {

    protected List<PowerUp> listaPowerUpsNivel;
    public PowerUp(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite);
        this.listaPowerUpsNivel = listaPowerUpsNivel;

    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return colisionan;
    }

    public int accept(Visitor v) {
       return v.visit(this);
    }


    public abstract int setEstadoMario(Jugador j);

    public void eliminarEntidad() {
        listaPowerUpsNivel.remove(this);
    }

}
