package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

public class PiranhaPlant extends Enemigo {

    protected VisitorEnemigo visitor;

    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        visitor = new VisitorEnemigo(this);
        velocidad = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        if (colisionan) {
            // Si hay colisión con el jugador, cambia el comportamiento a atacar
            setComportamientoIA(new IAAtacar());
        }
        return colisionan;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
