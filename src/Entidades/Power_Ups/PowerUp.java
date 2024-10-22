package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

public abstract class PowerUp extends EntidadMovil {

    protected int puntaje;
    public PowerUp(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return colisionan;
    }

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        this.setPosicionEnY(1000);
    }

    public void setEstadoMario(Jugador j){

    }

}
