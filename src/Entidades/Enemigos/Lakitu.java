package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.Visitor;

public class Lakitu extends Enemigo {

    public Lakitu(int x, int y, Sprite sprite) {
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

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mario.getEstadoJugador().esInmortal()) {
            if (mario.colisionAbajo(this)) {
                this.setAEliminar();
                this.setPosicionEnY(-100);
                toReturn = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                if (mario.getEstadoJugador().esInmortal()) {
                    this.setAEliminar();
                    toReturn = (ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO);
                }
            }
        }
        return toReturn;
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
    }

}
