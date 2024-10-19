package Entidades.Power_Ups;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorPowerUp;

public class FlorDeFuego extends PowerUp {

    protected VisitorPowerUp visitor;
    protected int puntaje;
    public FlorDeFuego(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorPowerUp(this);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
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
}
