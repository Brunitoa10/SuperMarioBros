package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Enemigos.EstadoLakitu.EstadoLakitu;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import IA.IAatacar;
import Visitor.Visitor;

import java.util.List;

public class Lakitu extends Enemigo {
protected Jugador jugador;
protected EstadoLakitu estadoLakitu;
    public Lakitu(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel, Jugador mario) {
        super(x, y, sprite, new IAatacar(mario), listaEnemigoNivel);
        velocidad = 4;
        jugador = mario;
    }

    public void actualizar() {
        this.actualizarEntidad();
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
            if (mario.colisionAbajo(this)) {
                this.setAEliminar();
                toReturn = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {

                    this.setAEliminar();
                    toReturn = (ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO);
                }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeLakituDestruido = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeLakituDestruido;
    }

}
