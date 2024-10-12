package Entidades.EntidadInmovil;

import Entidades.Colisionable;
import Fabricas.Sprite;
import Logica.Nivel;
import Visitor.Visitor;

public class Moneda extends EntidadInmovil{


    public Moneda(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void addPuntaje(Nivel nivel) {

        int puntaje = 1;
        nivel.addPuntaje(puntaje);

    }


    public boolean detectColision(Colisionable c) {
        return c.detectColision(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
