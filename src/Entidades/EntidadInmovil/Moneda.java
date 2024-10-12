package Entidades.EntidadInmovil;

import Entidades.Colisionable;
import Entidades.Entidad;
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


    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
