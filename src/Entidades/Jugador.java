package Entidades;

import java.util.Random;

import EstadoJugador.EstadoJugador;
import EstadoJugador.Mario;
import EstadoMovimiento.EstadoMovimiento;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Visitor.Visitor;
import Visitor.VisitorJugador;

public class Jugador extends EntidadMovil implements EntidadJugador {

    protected static final int ALTURA_MAXIMA_SALTO = 200;
    protected EstadoJugador estadoJugador;
    protected EstadoJugador estadoJugadorAnterior;
    protected EstadoMovimiento estadoMovimiento;
    protected VisitorJugador VisitorJugador;
    protected boolean enPlataforma;
    protected Sonido sonido;
    protected boolean estaVivo;
    protected int puntaje;
    protected boolean muerte;
    protected int vidas;

    public Jugador(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        VisitorJugador = new VisitorJugador(this);
        this.puntaje = 0;
        this.velocidad = 4;
        this.estadoJugador = new Mario(this);
        this.estadoMovimiento = new MarioParado(this);
        this.enPlataforma = false;
        this.estaVivo = true;
        muerte=false;
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
    public void actualizarEntidad() {
        estadoMovimiento.actualizar();
    }


    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void desplazarEnX(int direccion) {
        if(direccion != 0)
            estadoMovimiento.desplazarEnX(direccion);
        if(posicionX < -800) {
            posicionX = -800;
        }
    }

    public void saltar() {
    	super.saltar();
        estadoMovimiento.saltar(this);
    }

    public VisitorJugador getVisitorJugador() {
        return VisitorJugador;
    }

    public boolean estaEnPlataforma() {
        return enPlataforma;
    }

    public void setEnPlataforma(boolean enPlataforma) {
        this.enPlataforma = enPlataforma;
    }

    public boolean puedeRomperBloques() {
        return getEstadoJugador().puedeRomperBloques();
    }
  
    public boolean puedeLanzarBolaDeFuego() {
        return estadoJugador.puedeLanzarBolaFuego();
    }

    public boolean estaVivo() {
        return vidas != 0;
    }

    public boolean getMorir(){
        return muerte;
    }

    public void setMorir(boolean morir) {
        muerte = morir;
    }

}
