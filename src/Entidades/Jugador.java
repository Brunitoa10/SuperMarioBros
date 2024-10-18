package Entidades;

import EstadoJugador.EstadoJugador;
import EstadoJugador.Mario;
import EstadoMovimiento.EstadoMovimiento;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Visitor.Visitor;
import Visitor.VisitorJugador;
import Vista.Controladores.ControlTeclado;

public class Jugador extends EntidadMovil implements EntidadJugador {

    protected static final int ALTURA_MAXIMA_SALTO = 200;
    protected ControlTeclado controlTeclado;
    protected EstadoJugador estadoJugador;
    protected EstadoJugador estadoJugadorAnterior;
    protected EstadoMovimiento estadoMovimiento;
    protected int puntaje;
    protected VisitorJugador VisitorJugador;
    protected boolean enPlataforma;
    protected Sonido sonido;

    public Jugador(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        VisitorJugador = new VisitorJugador(this);
        this.puntaje = 0;
        this.velocidad = 3;
        this.estadoJugador = new Mario(this);
        this.estadoMovimiento = new MarioParado(this);
        this.controlTeclado = new ControlTeclado(this);
        this.enPlataforma = false;
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
    public void actualizarEntidad() {
        estadoMovimiento.actualizar();
    }



    @Override
    public int get_tiempo() {
        return 300;
    }

    @Override
    public int get_vida() {
        return 3;
    }

    @Override
    public void accept(Visitor v) {

    }

    public void desplazarEnX(int direccion) {
        estadoMovimiento.desplazarEnX(direccion);
        if(posicionX < -800) {
            posicionX = -800;
        }
    }

    public void saltar() {

    	sonido = SonidoFactory.crearSonido("salto");
    	sonido.reproducir();
        estadoMovimiento.saltar(this);
    }

    public int getAlturaMaximaSalto() {

        return ALTURA_MAXIMA_SALTO;
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
}
