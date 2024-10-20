package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import EstadoMovimiento.MarioEnAire;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.Visitor;

public class BuzzyBeetle extends Enemigo {

    private int puntajeMuerteMario;

    public BuzzyBeetle(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        velocidad = 4;
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

    public void interactuar(Jugador mario) {
        if(mario.colisionAbajo(this)) {
            this.aEliminar = true;
            mario.setEstadoMovimiento(new MarioEnAire(mario));
            //mario.setPuntaje(mario.getPuntaje()+ConstantesPuntaje.PUNTAJE_BUZZY_BEETLE_DESTRUIDO);
        }
        else if(mario.colisionDerecha(this)) {

        } else if(mario.colisionIzquierda(this)) {

        }
    }
}
