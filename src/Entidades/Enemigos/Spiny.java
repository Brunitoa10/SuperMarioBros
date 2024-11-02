package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Enemigos.EstadoSpiny.EstadoSpiny;
import Entidades.Enemigos.EstadoSpiny.SpinyCayendo;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.VisitorEnemigo;

import java.util.List;

public class Spiny extends Enemigo {

    protected EstadoSpiny estadoSpiny;
    protected VisitorEnemigo visitor;
    boolean estoyEnPlataforma;

    public Spiny(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        estadoSpiny = new SpinyCayendo(this);
    }

    public void actualizar() {
        estadoSpiny.actualizarEstadoSpiny();
        super.actualizar();
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        mario.getEstadoJugador().recibeDanio(this);
        if (mario.getMorir())
            toReturn = ConstantesPuntaje.PUNTAJE_SPINY_MUERTE_MARIO;
        else if (this.aEliminar()) {
            toReturn = ConstantesPuntaje.PUNTAJE_SPINY_DESTRUIDO;
        }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        return super.interactuarConProyectil(proyectil) + ConstantesPuntaje.PUNTAJE_SPINY_DESTRUIDO;
    }

    public EstadoSpiny getEstadoSpiny() {
        return estadoSpiny;
    }

    public void setEstadoSpiny(EstadoSpiny estadoSpiny) {
        this.estadoSpiny = estadoSpiny;
    }

}
