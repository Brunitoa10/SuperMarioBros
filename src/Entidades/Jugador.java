package Entidades;
import Fabricas.Sprite;
import EstadoJugador.*;
import EstadoMovimiento.*;

public class Jugador extends EntidadMovil implements EntidadJugador {

    protected EstadoJugador estadoJugador;
    protected EstadoJugador estadoJugadorAnterior;
    protected EstadoMovimiento estadoMovimiento;
    protected int puntaje;

    public Jugador(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.puntaje = 0;
        this.velocidad = 4;
        this.estadoJugador = new Mario();
        this.estadoMovimiento = new MarioParado();
    }

    public EstadoJugador getEstadoJugador() {
        return estadoJugador;
    }

    public void setEstadoJugador(EstadoJugador estadoJugador) {
        this.estadoJugador = estadoJugador;
    }

    public EstadoJugador getEstadoJugadorAnterior() {
        return estadoJugadorAnterior;
    }

    public void setEstadoJugadorAnterior(EstadoJugador estadoJugadorAnterior) {
        this.estadoJugadorAnterior = estadoJugadorAnterior;
    }

    public EstadoMovimiento getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    @Override
    public int get_puntaje() {
        return puntaje;
    }

    @Override
    public void actualizar_entidad() {

    }
}
