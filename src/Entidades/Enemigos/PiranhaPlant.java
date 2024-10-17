package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class PiranhaPlant extends Enemigo {
    
    private int puntajeMuerteMario;

    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        velocidad = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
