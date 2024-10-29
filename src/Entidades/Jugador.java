package Entidades;

import Constantes.CadenasValidacion;
import EstadoJugador.Puntajes;
import EstadoJugador.EstadoJugador;
import EstadoJugador.Mario;
import EstadoMovimiento.EstadoMovimiento;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Visitor.Visitor;
import Visitor.VisitorJugador;

public class Jugador extends EntidadMovil implements EntidadJugador {

    protected EstadoJugador estadoJugador;
    protected EstadoMovimiento estadoMovimiento;
    protected VisitorJugador VisitorJugador;
    protected boolean enPlataforma;
    protected Sonido sonido;
    protected boolean estaVivo;
    protected boolean muerte;
    protected boolean sumarVida;
    protected static final Puntajes MATRIZPUNTAJE=new Puntajes();

    public Jugador(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.velocidad = 12;
        inicializacionEstadosYVisitantes();
        inicializacionBooleanos();

    }

    public EstadoJugador getEstadoJugador() {
        return estadoJugador;
    }

    public void setEstadoJugador(EstadoJugador estadoJugador) {
        this.estadoJugador = estadoJugador;
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
    public void eliminarEntidad() {
        //mario nunca se elimina
    }

    @Override
    public int accept(Visitor v) {
        return v.visit(this);
    }

    public void desplazarEnX(int direccion) {
        if (direccion != 0)
            estadoMovimiento.desplazarEnX(direccion);
        if (posicionX < -800) {
            posicionX = -800;
        }
    }

    public void saltar() {
        super.saltar();
        estadoMovimiento.saltar();
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

    public boolean getMorir() {
        return muerte;
    }

    public void setMorir(boolean morir) {
        muerte = morir;
    }

    public void sumarUnaVida(boolean deberiaSumarUnaVida) {
        sumarVida = deberiaSumarUnaVida;
    }

    public boolean debeSumarUnaVida() {
        return sumarVida;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    private void inicializacionEstadosYVisitantes() {
        this.VisitorJugador = new VisitorJugador(this);
        this.estadoJugador = new Mario(this);
        this.estadoMovimiento = new MarioParado(this);
    }

    private void inicializacionBooleanos() {
        this.enPlataforma = false;
        this.estaVivo = true;
        muerte = false;
        sumarVida = false;
    }

    public Puntajes getPuntajes(){
        return MATRIZPUNTAJE;
    }

}
