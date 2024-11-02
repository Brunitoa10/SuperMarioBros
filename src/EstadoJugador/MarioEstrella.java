package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;
import Logica.Temporizador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MarioEstrella implements EstadoJugador {


    protected Jugador mario;
    protected EstadoJugador estadoAnterior;
    protected int alto;
    protected static final int FILA=3;
    protected Temporizador temporizador;
    protected boolean cambioAFlorDeFuego = false;

    public MarioEstrella(Jugador mario) {
        estadoAnterior = mario.getEstadoJugador();
        this.mario = mario;
        alto = (int) mario.getHitbox().getHeight();
        mario.setPosicionEnY(mario.getPosicionEnY() - 32 + alto);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.setVelocidad(6);
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    public void recibeDanio(Entidad e) {
        e.setAEliminar();
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }


    public void actualizarEstadoJugador() {
        if (temporizador.hanPasadoNSegundos(7000)) {
            temporizador.resetear();
            mario.setVelocidad(4);
            mario.setPosicionEnY(mario.getPosicionEnY() + 32 - alto);
            mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, alto);
            if(!cambioAFlorDeFuego)
                mario.setEstadoJugador(estadoAnterior);
            else
                mario.setEstadoJugador(new SuperMarioFuego(mario));
        }
    }

    public String inicioAnimacion(){
        return mario.getSprite().getRutaModo() + "/Jugador/PNGMario/MarioEstrella";
    }

    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public String finalAnimacion() {
        return ".gif";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }

    @Override
    public int interactuar(SuperChampinion powerUp, int COLUMNA) {
        EstadoJugador estadoAuxiliar=this;
        estadoAnterior.interactuar(powerUp,COLUMNA);
        mario.setPosicionEnY(mario.getPosicionEnY()+18);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.setEstadoJugador(estadoAuxiliar);
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(FlorDeFuego powerUp, int COLUMNA) {
        cambioAFlorDeFuego=true;
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(Estrella powerUp, int COLUMNA) {
        mario.setEstadoJugador(new MarioEstrella(mario));
        return getPuntaje(COLUMNA);
    }
}
