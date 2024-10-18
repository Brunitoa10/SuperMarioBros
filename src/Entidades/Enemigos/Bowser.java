package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Visitor.Visitor;

public class Bowser extends Enemigo {

    public Bowser(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        velocidad = 4;
    }

    @Override
    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectColission(this);
        if (colisionan) {
            setComportamientoIA(new IAAtacar());
        }
        return colisionan;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
