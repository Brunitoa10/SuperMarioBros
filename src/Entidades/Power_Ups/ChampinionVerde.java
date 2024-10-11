package Entidades.Power_Ups;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Logica.Nivel;
import Visitor.Visitor;

public class ChampinionVerde extends PowerUp {

    public ChampinionVerde(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public void aplicarEfecto(Nivel nivel){
        nivel.sumarVida(1);
    }

    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
