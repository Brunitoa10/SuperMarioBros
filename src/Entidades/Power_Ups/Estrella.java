package Entidades.Power_Ups;
import Entidades.Colisionable;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Estrella extends PowerUp {

    public Estrella(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
