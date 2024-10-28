package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IAPiranhaPlant;
import Logica.Temporizador;
import Visitor.VisitorEnemigo;

import java.util.List;

public class PiranhaPlant extends Enemigo {

    protected VisitorEnemigo visitor;
    protected int yEscondido;
    protected int yMaximo;
    protected IAPiranhaPlant iapiranhaPlant;
    protected boolean subiendo;
    protected boolean bajando;
    protected Temporizador temporizadorSubirBajar;


    public PiranhaPlant(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IAPiranhaPlant(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        yEscondido = y;
        yMaximo = yEscondido - (int) this.getHitbox().getHeight();
        temporizadorSubirBajar = new Temporizador();
        temporizadorSubirBajar.iniciar();
    }

    public void actualizar() {
        if (temporizadorSubirBajar.hanPasadoNSegundos(10000)) {
            if (estaEscondido()) {
                subiendo = true;
            } else if (estaEnYMaximo()) {
                bajando = true;
            }
        }
        if (subiendo) {
            subirPlanta();
            if (estaEnYMaximo()) {
                subiendo = false;
                temporizadorSubirBajar.resetear();
                temporizadorSubirBajar.iniciar();
            }
        }
        if (bajando) {
            bajarPlanta();
            if (estaEscondido()) {
                bajando = false;
                temporizadorSubirBajar.resetear();
                temporizadorSubirBajar.iniciar();
            }
        }
        super.actualizarEntidad();
    }

    public int interactuar(Jugador mario) {
        int toRet = 0;
        if ((mario.colisionAbajo(this) || mario.colisionDerecha(this)
                || mario.colisionIzquierda(this)) && !estaEscondido()) {
            mario.getEstadoJugador().recibeDanio(this);
            if (mario.getMorir()) {
                toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_MUERTE_MARIO;
            } else if (this.aEliminar()) {
                toRet = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
            }
        }
        return toRet;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntaje = 0;
        if (!estaEscondido()) {
            this.setAEliminar();
            proyectil.setDireccion(0);
            puntaje = ConstantesPuntaje.PUNTAJE_PIRANHA_PLANT_DESTRUIDA;
            ;
        }
        return puntaje;
    }


    public boolean estaEscondido() {
        return yEscondido == posicionY;
    }

    public boolean estaEnYMaximo() {
        return yMaximo == posicionY;
    }

    private void subirPlanta() {
        this.setPosicionEnY(this.getPosicionEnY() - velocidad);
    }

    private void bajarPlanta() {
        this.setPosicionEnY(this.getPosicionEnY() + velocidad);
    }
}
