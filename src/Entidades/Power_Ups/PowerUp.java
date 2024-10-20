package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPowerUp;

public abstract class PowerUp extends EntidadMovil {

    protected int puntaje;
    protected VisitorPowerUp visitor;
    public PowerUp(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorPowerUp(this);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return colisionan;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public VisitorPowerUp getVisitor() {
        return visitor;
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
