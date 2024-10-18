package Entidades.Enemigos;

import Entidades.Entidad;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Visitor.Visitor;

public class Spiny extends Enemigo {
    
    private int puntajeMuerteMario;

    public Spiny(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
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
